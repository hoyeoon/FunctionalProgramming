package chapter8;

import chapter8.model.User;
import chapter8.service.EmailService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Partitioning By
 *
 * 1번 partitioningBy : public static <T>
 *                          Collector<T, ?, Map<Boolean, List<T>>> partitioningBy(
 *                              Predicate<? super T> predicate)
 *
 * 2번 partitioningBy : public static <T, D, A>
 *                          Collector<T, ?, Map<Boolean, D>> partitioningBy(
 *                              Predicate<? super T> predicate,
 *                              Collector<? super T, A, D> downstream)
 *
 * - GroupingBy와 유사하지만 Function 대신 Predicate을 받아 true와 false 두 key가 존재하는 map을 반환하는 collector
 * - 마찬가지로 downstream collector를 넘겨 List 이외의 형태로 map의 value를 만드는 것 역시 가능
 */

public class Chapter8Section8 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(13, 2, 101, 203, 304, 402, 305, 349, 2312, 203);
        Map<Boolean, List<Integer>> numberPartitions = numbers.stream()
                .collect(Collectors.partitioningBy(number -> number % 2 == 0));
        System.out.println("Even number = " + numberPartitions.get(true));
        System.out.println("Odd number = " + numberPartitions.get(false));

        // 예제 - User
        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setEmailAddress("alice@gmail.com")
                .setFriendUserIds(Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214));
        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setEmailAddress("bob@gmail.com")
                .setFriendUserIds(Arrays.asList(204, 205, 206));
        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setEmailAddress("charlie@gmail.com")
                .setFriendUserIds(Arrays.asList(204, 205, 207, 218));
        List<User> users = Arrays.asList(user1, user2, user3);

        Map<Boolean, List<User>> userPartitions = users.stream()
                .collect(Collectors.partitioningBy(user -> user.getFriendUserIds().size() > 5));

        EmailService emailService = new EmailService();
        
        // 기존 방식
/*
        for(User user : userPartitions.get(true)) {
            emailService.sendPlayWithFriendsEmail(user);
        }

        for(User user : userPartitions.get(false)) {
            emailService.sendMakeMoreFriendsEmail(user);
        }
*/
        // forEach 적용
        userPartitions.get(true).stream()
                .forEach(emailService::sendPlayWithFriendsEmail);

        userPartitions.get(false).stream()
                .forEach(emailService::sendMakeMoreFriendsEmail);
    }
}
