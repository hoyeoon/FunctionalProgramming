package chapter8;

import chapter8.model.User;
import chapter8.model.Order;
import chapter8.model.Order.OrderStatus;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static chapter8.model.Order.OrderStatus.*;

/**
 * To Map
 *
 * 선언부
 * public static <T, K, U> Collector<T, ?, Map<K, U>> toMap(
 *      Function<? super T, ? extends K> keyMapper,
 *      Function<? super T, ? extends U> valueMapper)
 *
 * toMap - Stream 안의 데이터를 map의 형태로 반환해주는 collector
 * keyMapper - 데이터를 map의 key로 변환하는 Function
 * valueMapper - 데이터를 map의 value로 변환하는 Function
 */
public class Chapter8Section6 {
    public static void main(String[] args) {
        // 예제 - 기본 사용법
        Map<Integer, String> numberMap = Stream.of(3, 5, -4, 2, 6)
                .collect(Collectors.toMap(Function.identity(), x -> "Number is " + x)); // Function.identity() == x -> x
        System.out.println("numberMap = " + numberMap);
        System.out.println("numberMap.get(3) = " + numberMap.get(3));

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

        Map<Integer, User> userIdToUserMap = users.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));
        System.out.println("userIdToUserMap = " + userIdToUserMap);
        System.out.println("userIdToUserMap.get(103) = " + userIdToUserMap.get(103));

        // 예제 - Order
        Order order1 = new Order()
                .setId(1001L)
                .setStatus(CREATED)
                .setAmount(BigDecimal.valueOf(2000));
        Order order2 = new Order()
                .setId(1002L)
                .setStatus(CREATED)
                .setAmount(BigDecimal.valueOf(4000));
        Order order3 = new Order()
                .setId(1003L)
                .setStatus(PROCESSED)
                .setAmount(BigDecimal.valueOf(3000));
        Order order4 = new Order()
                .setId(1004L)
                .setStatus(CREATED)
                .setAmount(BigDecimal.valueOf(7000));
        List<Order> orders = Arrays.asList(order1, order2, order3, order4);

        // TODO: Create a map from order id to order status
        Map<Long, OrderStatus> orderIdToOrderStatusMap = orders.stream()
                .collect(Collectors.toMap(Order::getId, Order::getStatus));
        System.out.println("orderIdToOrderStatusMap = " + orderIdToOrderStatusMap);
        System.out.println("orderIdToOrderStatusMap.get(1001) = " + orderIdToOrderStatusMap.get(1001L));
    }
}