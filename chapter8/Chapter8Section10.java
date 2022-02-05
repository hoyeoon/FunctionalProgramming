package chapter8;

import chapter8.model.User;
import chapter8.service.EmailService;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Parallel Stream - Stream을 병렬로
 *
 * List<Integer> numbers = Arrays.asList(1, 2, 3);
 * Stream<Integer> parallelStream = numbers.parallelStream();
 * Stream<Integer> parallelStream2 = numbers.stream().parallel();
 *
 * - Sequential vs Parallel
 * - 여러 개의 스레드를 이용하여 Stream의 처리 과정을 병렬화 (Parallelize)
 * - 중간 과정은 병렬처리 되지만, 순서가 있는 Stream의 경우 종결 처리 했을 때의
 *   결과물이 기존의 순차적 처리와 일치하도록 종결처리 과정에서 조정된다.
 *   즉, List로 collect 한다면 순서가 항상 올바르게 나온다는 것.
 *
 *   장점
 *   - 굉장히 간단하게 병렬 처리를 사용할 수 있게 해준다.
 *   - 속도가 비약적으로 빨라 질 수 있다.
 *
 *   단점
 *   - 항상 속도가 빨라지는 것은 아니다.
 *   - 공통으로 사용하는 리소스가 있을 경우 잘못된 결과가 나오거나
 *     아예 오류가 날 수도 있다.(deadlock)
 *   - 이를 막기 위해 mutex, semaphore 등 병렬 처리 기술을 이용하면
 *     순차처리보다 느려질 수도 있다.
*/
public class Chapter8Section10 {
    public static void main(String[] args) {
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
        User user4 = new User()
                .setId(104)
                .setName("David")
                .setVerified(true)
                .setEmailAddress("david@gmail.com");
        User user5 = new User()
                .setId(105)
                .setName("Eve")
                .setVerified(false)
                .setEmailAddress("eve@gmail.com");
        User user6 = new User()
                .setId(106)
                .setName("Frank")
                .setVerified(false)
                .setEmailAddress("frank@gmail.com");
        List<User> users = Arrays.asList(user1, user2, user3, user4, user5, user6);

        long startTime = System.currentTimeMillis();
        EmailService emailService = new EmailService();
        users.stream()
                .filter(user -> !user.isVerified())
                .forEach(emailService::sendVerifyYourEmail); // .forEach(user -> emailService.sendVerifyYourEmail(user));
        long endTime = System.currentTimeMillis();
        System.out.println("Sequential: " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        users.stream().parallel()
                .filter(user -> !user.isVerified())
                .forEach(emailService::sendVerifyYourEmail); // .forEach(user -> emailService.sendVerifyYourEmail(user));
        endTime = System.currentTimeMillis();
        System.out.println("Sequential: " + (endTime - startTime) + "ms");

        // 예제 2
        // 중간 처리 과정에서 순서는 뒤죽박죽이지만, 최종 결과는 순서를 맞추어 준다.
        // 중간 처리 과정이 순서에 민감한 작업이라면, parallel stream을 쓰기가 힘들다.
        // mutex등을 이용해 해결할 수도 있지만, 오히려 속도가 더 느려질 것이다.
        List<User> processedUsers = users.parallelStream()
                .map(user -> {
                    System.out.println("Capitalize user name for user " + user.getId());
                    user.setName(user.getName().toUpperCase());
                    return user;
                })
                .map(user -> {
                    System.out.println("Set 'isVerified' to true for user " + user.getId());
                    user.setVerified(true);
                    return user;
                })
                .collect(Collectors.toList());
        System.out.println("processedUsers = " + processedUsers);
    }
}