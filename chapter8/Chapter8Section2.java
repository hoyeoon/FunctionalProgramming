package chapter8;

import chapter8.model.Order;
import chapter8.model.User;

import java.math.BigDecimal;
import java.util.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import static chapter8.model.Order.OrderStatus.*;

/**
 * allMatch : Stream 안의 모든 데이터가 predicate을 만족하면 true
 * anyMatch : Stream 안의 데이터 중 하나라도 predicate을 만족하면 true
 */
public class Chapter8Section2 {
    public static void main(String[] args) {
        // 예제 1
        List<Integer> numbers = Arrays.asList(3, -4, 2, 7, 9);
        boolean allPositive = numbers.stream()
                .allMatch(number -> number > 0);
        System.out.println("allPositive = " + allPositive);

        boolean anyNegative = numbers.stream()
                .anyMatch(number -> number < 0);
        System.out.println("anyNegative = " + anyNegative);

        // User
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setVerified(true)
                .setEmailAddress("alice@gmail.com")
                .setCreatedAt(now.minusDays(2));;
        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setVerified(false)
                .setEmailAddress("bob@gmail.com")
                .setCreatedAt(now.minusHours(10));;
        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setVerified(false)
                .setEmailAddress("charlie@gmail.com")
                .setCreatedAt(now.minusHours(1));
        User user4 = new User()
                .setId(104)
                .setName("David")
                .setVerified(true)
                .setEmailAddress("david@gmail.com")
                .setCreatedAt(now.minusHours(27));

        List<User> users = Arrays.asList(user1, user2, user3);

        // 예제 2
        boolean areAllUserVerified = users.stream()
                .allMatch(User::isVerified);
        System.out.println("areAllUserVerified = " + areAllUserVerified);

        // Order
        Order order1 = new Order()
                .setId(1001)
                .setStatus(CREATED)
                .setAmount(BigDecimal.valueOf(2000))
                .setCreatedByUserId(101);
        Order order2 = new Order()
                .setId(1002)
                .setStatus(ERROR)
                .setAmount(BigDecimal.valueOf(4000))
                .setCreatedByUserId(102);
        Order order3 = new Order()
                .setId(1003)
                .setStatus(PROCESSED)
                .setAmount(BigDecimal.valueOf(3000))
                .setCreatedByUserId(103);
        Order order4 = new Order()
                .setId(1004)
                .setStatus(CREATED)
                .setAmount(BigDecimal.valueOf(7000))
                .setCreatedByUserId(104);
        Order order5 = new Order()
                .setId(1005)
                .setStatus(IN_PROGRESS)
                .setAmount(BigDecimal.valueOf(7000))
                .setCreatedByUserId(105);
        List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);

        // TODO: check if any of orders is in ERROR status
        boolean isAnyOrderInErrorStatus = orders.stream()
                .anyMatch(order -> order.getStatus() == ERROR);
        System.out.println("isAnyOrderInErrorStatus = " + isAnyOrderInErrorStatus);
    }
}