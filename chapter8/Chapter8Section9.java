package chapter8;

import chapter8.model.User;
import chapter8.service.EmailService;

import java.util.*;
import java.util.stream.IntStream;

/**
 * For Each
 *
 * void forEach(Consumer<? super T> action);
 *
 * - 제공된 action을 Stream의 각 데이터에 적용해주는 종결 처리 메서드
 * - Java의 iterable 인터페이스에도 forEach가 있기 때문에 Stream의 중간 처리가
 *   필요없다면 iterable collection(Set, List 등)에서 바로 쓰는 것도 가능
 */
public class Chapter8Section9 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 5, 2, 1);
        numbers.stream().forEach(number ->
                System.out.println("The number is " + number));

        // 중간처리가 필요없다면 stream() 없이 바로 사용 가능.
        // List도 iterable이기 때문에 forEach를 implement 해놓았기 때문.
        numbers.forEach(number ->
                System.out.println("The number is " + number));

        // 예제 - User
        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setVerified(true)
                .setEmailAddress("alice@gmail.com");
        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setVerified(false)
                .setEmailAddress("bob@gmail.com");
        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setVerified(false)
                .setEmailAddress("charlie@gmail.com");
        List<User> users = Arrays.asList(user1, user2, user3);

        EmailService emailService = new EmailService();

        // 기존 방식
        for(User user : users) {
            if(!user.isVerified()) {
                emailService.sendVerifyYourEmail(user);
            }
        }

        // forEach 기본 사용법
        users.stream()
                .filter(user -> !user.isVerified())
                .forEach(emailService::sendVerifyYourEmail); // .forEach(user -> emailService.sendVerifyYourEmail(user));

        /**
         * 반복문에서 index를 하나씩 가져오고 싶을 때 : IntStream
         */
        // 기존 방식
        for(int i = 0; i < users.size(); i++){
            User user = users.get(i);
            System.out.println("Do an operation on user " + user.getName() + " at index " + i);
        }

        // IntStream
        IntStream.range(0, users.size()).forEach(i -> {
            User user = users.get(i);
            System.out.println("Do an operation on user " + user.getName() + " at index " + i);
        });
    }
}
