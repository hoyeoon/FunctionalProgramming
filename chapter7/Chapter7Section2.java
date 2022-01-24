package chapter7;

import chapter7.model.User;
import java.util.Optional;
/**
 * Optional - 응용을 위해 알아야 할 것들
 * - ifPresent : Optional 이 null 이 아니라면 action 을 실행
 * - map : Optional 이 null 이 아니라면 mapper 를 적용
 * - flatMap : mapper 의 리턴 값이 또 다른 Optional 이라면 한 단계의 Optional 이 되도록 납작하게 해줌
 */
public class Chapter7Section2 {
    public static void main(String[] args) {
        Optional<User> maybeUser = Optional.ofNullable(maybeGetUser((true)));
        maybeUser.ifPresent(user -> System.out.println(user));

        Optional<Integer> maybeId = Optional.ofNullable(maybeGetUser(true))
                .map(user -> user.getId());
        maybeId.ifPresent(System.out::println);

        String maybeUserName = Optional.ofNullable(maybeGetUser(false))
                .map(User::getName)
                .map(name -> "The name is " + name)
                .orElse("Name is empty");
        System.out.println("maybeUserName = " + maybeUserName);

        // 우리가 원하는 것은 Optional<Optional<String>>이 아니라 Optional<String>일 것이다.
//        Optional<Optional<String>> maybeEmail = Optional.ofNullable(maybeGetUser(false))
//                .map(User::getEmailAddress);

        Optional<String> maybeEmail = Optional.ofNullable(maybeGetUser(true))
                .flatMap(User::getEmailAddress);
        maybeEmail.ifPresent(System.out::println);
    }

    public static User maybeGetUser(boolean returnUser) {
        if(returnUser){
            return new User()
                    .setId(1001)
                    .setName("Alice")
                    .setEmailAddress("alice@gmail.com")
                    .setVerified(false);
        }
        return null;
    }
}
