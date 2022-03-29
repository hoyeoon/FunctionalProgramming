package chapter10;

import chapter10.model.User;
import chapter10.service.InternalUserService;
import chapter10.service.UserService;
import chapter10.service.UserServiceInFunctionalWay;

import java.util.Arrays;

/**
 * Template Method Pattern
 * - 또 하나의 대표적인 행동 패턴
 * - 상위 클래스는 알고리즘의 뼈대만을 정의하고 알고리즘의 각 단계는 하위 클래스에게 정의를 위임하는 패턴
 * - 알고리즘의 구조를 변경하지 않고 세부 단계들을 유연하게 변경할 수 있게 해준다.
 */
public class Chapter10Section5 {
    public static void main(String[] args) {
        User alice = User.builder(1, "Alice")
                .with(builder -> {
//                    builder.emailAddress = "alice@gmail.com";
                    builder.isVerified = false;
                    builder.friendUserIds = Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214);
                }).build();

        UserService userService = new UserService();
        InternalUserService internalUserService = new InternalUserService();

        userService.createUser(alice);
        internalUserService.createUser(alice);

        /**
         * 람다를 활용하여 다음과 같이 Template Method Pattern을 클래스 생성없이 유연하게 적용시켜 만들 수 있다.
         */
        UserServiceInFunctionalWay userServiceInFunctionalWay = new UserServiceInFunctionalWay(
               user -> {
                   System.out.println("Validating user " + user.getName());
                   return user.getName() != null && user.getEmailAddress().isPresent();
               },
                user -> {
                    System.out.println("Writing user " + user.getName() + " to internal DB");
                }
        );
        userServiceInFunctionalWay.createUser(alice);
    }
}
