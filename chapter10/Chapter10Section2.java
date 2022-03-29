package chapter10;

import chapter10.model.User;

/**
 * Builder Pattern
 * - 대표적인 생성 패턴
 * - 객체의 생성에 대한 로직과 표현에 대한 로직을 분리해준다.
 * - 객체의 생성 과정을 유연하게 해준다.
 * - 객체의 생성 과정을 정의하고 싶거나 필드가 많아 constructor가 복잡해질 때 유용
 */
public class Chapter10Section2 {
    public static void main(String[] args) {
        // 람다 사용 이전 - with 메서드 활용
/*        User user = User.builder(1, "Alice")
                .withEmailAddress("alice@gmail.com")
                .withVerified(true)
                .build();
        System.out.println("user = " + user);*/

        User user = User.builder(1, "Alice")
                .with(builder -> {
                    builder.emailAddress = "alice@gmail.com";
                    builder.isVerified = true;
                }).build();
        System.out.println("user = " + user);
    }
}
