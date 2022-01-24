package chapter7;

import chapter7.model.User;
import java.util.Optional;

/**
 * Optional
 * - Null 일수도, 아닐 수도 있는 오브젝트를 담은 상자
 *
 * 만드는 법
 * - of : Null 이 아닌 오브젝트를 이용해 Optional 을 만들 때
 * - Empty : 빈 Optional 을 만들 때
 * - ofNullable - Null 인지 아닌지 알지 못하는 오브젝트로 Optional 을 만들 때
 *
 * 안에 있는 값을 확인하고 꺼내는 법
 * - isPresent : 안의 오브젝트가 Null 인지 아닌지 체크, Null 이 아닐 시 true
 * - get : Optional 안의 값을 추출. Null 이라면 에러
 * - orElse : Optional 이 Null 이 아니라면 Optional 안의 값을, Null 이라면 other 로 공급된 값을 리턴
 * - orElseGet : Optional 이 Null 이 아니라면 Optional 안의 값을, Null 이라면 supplier 로 공깁되는 값을 리턴
 * - orElseThrow : Optional 이 Null 이 아니라면 Optional 안의 값을, Null 이라면 exceptionSupplier 로 공급되는 exception을 던짐
 */
public class Chapter7Section1 {
    public static void main(String[] args) {
        // 예제 1 - NullPointerException
        User user1 = new User()
                .setId(1001)
                .setName("Alice")
                .setVerified(false);

        User user2 = new User()
                .setId(1001)
                .setName("Alice")
                .setEmailAddress("alice@gmail.com")
                .setVerified(false);

        // NullPointerException 발생! user1은 emailAddress가 지정되지 않음.
        // System.out.println("Same? :" + userEquals(user1, user2));
        System.out.println("Same? :" + userEquals(user2, user1));

        String someEmail = "some@email.com";
        String nullEmail = null;

        // 예제 2 - Optional
        Optional<String> maybeEmail = Optional.of(someEmail);
        Optional<String> maybeEmail2 = Optional.empty();
        Optional<String> maybeEmail3 = Optional.ofNullable(someEmail);
        Optional<String> maybeEmail4 = Optional.ofNullable(nullEmail);

        String email = maybeEmail.get();
        System.out.println("email = " + email);
//        String email2 = maybeEmail2.get();   // 에러 발생!

        // Optional 안에는 값이 있을 수도 있고 없을 수도 있다.
        // 따라서, 보통 값에 접근하기 전에, 안에 값이 있는지 check 한다.
        if(maybeEmail2.isPresent()) {
            System.out.println("maybeEmail2.get() = " + maybeEmail2.get());
        }

        String defaultEmail = "default@email.com";
        String email3 = maybeEmail2.orElse(defaultEmail);
        System.out.println("email3 = " + email3);

        String email4 = maybeEmail2.orElseGet(() -> defaultEmail);
        System.out.println("email4 = " + email4);

        String email5 = maybeEmail2.orElseThrow(() -> new RuntimeException("email not present"));
    }

    public static boolean userEquals(User u1, User u2) {
        return u1.getId() == u2.getId()
                && u1.getName().equals(u2.getName())
                && u1.getEmailAddress().equals(u2.getEmailAddress())
                && u1.isVerified() == u2.isVerified();
    }
}
