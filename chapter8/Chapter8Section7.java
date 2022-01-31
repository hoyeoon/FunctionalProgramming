package chapter8;

import chapter8.model.Order;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static chapter8.model.Order.OrderStatus.*;

/**
 * Grouping By
 *
 * 1번 groupingBy : public static <T, K> Collector<T, ?, Map<K, List<T>>>
 *                      groupingBy(Function<? super T, ? extends K> classifier)
 * - Stream 안의 데이터에 classifier를 적용했을 때 결과 값이 같은 값끼리 List로 모아서 Map의 형태로 반환해주는 collector.
 *      이 때 key는 classifier의 결과 값, value는 그 결과 값을 갖는 데이터들이다.
 * - Ex. stream에 {1,2,5,7,9,12,13}이 있을 때 classifier가 x -> x % 3 이라면
 *      반환되는 map은 {0=[9,12], 1=[1,7,13], 2=[2,5]}
 *
 * 2번 groupingBy : public static <T, K, A, D> Collector<T, ?, Map<K, D>> groupingBy(
 *                      Function<? super T, ? extends K> classifier,
 *                      Collector<? super T, A, D> downstream)
 * - 두 번째 매개변수로 downstream collector를 넘기는 것도 가능
 * - 그 경우 List 대신 collector를 적용시킨 값으로 map의 value가 만들어짐
 * - 이 때 자주 쓰이는 것이 mapping / reducing 등의 collector
 */
public class Chapter8Section7 {
    public static void main(String[] args) {
        // 예제 - 기본 사용법
        List<Integer> numbers = Arrays.asList(13, 2, 101, 203, 304, 402, 305, 349, 2312, 203);
        Map<Integer, List<Integer>> unitDigitMap = numbers.stream()
                .collect(Collectors.groupingBy(number -> number % 10));
        System.out.println("unitDigitMap = " + unitDigitMap);

        Map<Integer, Set<Integer>> unitDigitSet = numbers.stream()
                .collect(Collectors.groupingBy(number -> number % 10, Collectors.toSet()));
        System.out.println("unitDigitSet = " + unitDigitSet);

        Map<Integer, List<String>> unitDigitStrMap = numbers.stream()
                .collect(Collectors.groupingBy(number -> number % 10,
                        Collectors.mapping(number -> "unit digit is " + number, Collectors.toList())));
        System.out.println("unitDigitStrMap = " + unitDigitStrMap);
        System.out.println("unitDigitStrMap.get(3) = " + unitDigitStrMap.get(3));

        // 예제 - Order
        Order order1 = new Order()
                .setId(1001L)
                .setStatus(ERROR)
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

        // TODO: Create a map from order status to the list of corresponding orders
        // TODO: order status 별로  orders 를 묶어서 map 만들기
        Map<Order.OrderStatus, List<Order>> orderStatusMap = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus));
        System.out.println("orderStatusMap = " + orderStatusMap);

        // TODO: order status 별로  orders 를 묶어서 amount의 합 구하는 map 만들기
        Map<Order.OrderStatus, BigDecimal> orderStatusToSumOfAmountMap = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus,
                        Collectors.mapping(Order::getAmount,
                                Collectors.reducing(BigDecimal.ZERO, (x, y) -> x.add(y)))));
        System.out.println("orderStatusToSumOfAmountMap = " + orderStatusToSumOfAmountMap);
    }
}
