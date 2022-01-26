package chapter8;

import chapter8.model.Order;
import chapter8.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static chapter8.model.Order.OrderStatus.*;

/**
 * 종결 처리
 * - 종결 처리를 통해 최종 결과물을 도출
 * - 종결 처리의 실행이 필요할 때 중간 처리들도 비로소 실행 (Lazy Evaluation)
 *
 * max : Stream 안의 데이터 중 최대값을 반환. Stream이 비어있다면, 빈 Optional을 반환
 * min : Stream 안의 데이터 중 최소값을 반환. Stream이 비어있다면, 빈 Optional을 반환
 * count : Stream 안의 데이터의 개수를 반환
 */
public class Chapter8Section1 {
    public static void main(String[] args) {
        // 예제 1
        Optional<Integer> max = Stream.of(5, 3, 6, 2, 1)
                .max(Integer::compareTo);    // .max((x, y) -> x - y);
        System.out.println("max.get() = " + max.get());

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

        // 예제 2 - User의 이름순 정렬 했을 때 가장 앞에 오는 사람
        User firstUser = users.stream()
                .min((u1, u2) -> u1.getName().compareTo(u2.getName()))
                .get();
        System.out.println("firstUser = " + firstUser);

        // 예제 3 - 양수가 몇 개 인지 알아보자
        long positiveIntegerCount = Stream.of(1, -4, 5, -3, 6)
                .filter(x -> x > 0)
                .count();
        System.out.println("positiveIntegerCount = " + positiveIntegerCount);

        // 예제 4 - 최근 24시간 이내에 가입한 User들 중에서 아직 검증되지 않은 User 들이 총 몇 명?
        long unverifiedUsersIn24Hrs = users.stream()
                .filter(user -> user.getCreatedAt().isAfter(now.minusDays(1)))
                .filter(user -> !user.isVerified())
                .count();
        System.out.println("unverifiedUsersIn24Hrs = " + unverifiedUsersIn24Hrs);

        // Order
        Order order1 = new Order()
                .setId(1001)
                .setStatus(CREATED)
                .setAmount(BigDecimal.valueOf(2000))
                .setCreatedByUserId(101);
        Order order2 = new Order()
                .setId(1002)
                .setStatus(CREATED)
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

        // TODO: find order with highest amount an in ERROR status
        Order erroredOrderWithMaxAmount = orders.stream()
                .filter(order -> order.getStatus() == ERROR)
                .max((o1, o2) -> o1.getAmount().compareTo(o2.getAmount()))  // 여기까지 하면 Optional 반환 값이다.
                .get();
        System.out.println("erroredOrderWithMaxAmount = " + erroredOrderWithMaxAmount);

        BigDecimal maxErroredAmount = orders.stream()
                .filter(order -> order.getStatus() == ERROR)
                .map(Order::getAmount)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);   // ERROR에 해당하는 값이 없어도 default 값을 만들어낼 수 있다.
        System.out.println("maxErroredAmount = " + maxErroredAmount);
    }
}