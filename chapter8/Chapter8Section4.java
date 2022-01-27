package chapter8;

import chapter8.model.Order;
import chapter8.model.OrderLine;
import chapter8.model.User;

import java.math.BigDecimal;
import java.util.*;

/**
 * Reduce
 * - 주어진 함수를 반복 적용해 Stream 안의 데이터를 하나의 값으로 합치는 작업
 * - Max / Min / Count도 사실 reduce의 일종
 *
 * 1번 reduce : Optional<T> reduce(BinaryOperator<T> accumulator);
 * - 주어진 accumulator를 이용해 데이터를 합침. Stream이 비어있을 경우 빈 Optional 반환
 * 
 * 2번 reduce : T reduce(T identity, BinaryOperator<T> accumulator);
 * - 주어진 초기값과 accumulator를 이용. 초기값이 있기 때문에 항상 반환 값이 존재
 * 
 * 3번 reduce : <U> U reduce(U identity,
 *                  BiFunction<U, ? super T, U> accumulator,
 *                  BinaryOperator<U> combiner);
 * - 합치는 과정에서 타입이 바뀔 경우 사용. map + reduce로 대체 가능
 */
public class Chapter8Section4 {
    public static void main(String[] args) {
        // 예제 - 1번 reduce
        List<Integer> numbers = Arrays.asList(1, 4, -2, -5, 3);
        int sum = numbers.stream()
                .reduce((x, y) -> x + y)
                .get();
        System.out.println("sum = " + sum);

        // Min 은 reduce의 일종이다.
        int min = numbers.stream()
                .reduce((x, y) -> x < y ? x : y)
                .get();
        System.out.println("min = " + min);

        // 예제 - 2번 reduce
        // 초기값이 있어 항상 값을 리턴하기 때문에 Optional이 아니다.
        int product = numbers.stream()
                .reduce(1, (x, y) -> x * y);
        System.out.println("product = " + product);

        // 예제 - 3번 reduce
        // 일반적으로는 map + reduce를 따로 쓴다.
        List<String> numberStrList = Arrays.asList("3", "2", "5", "-4");
        int sumOfNumberStrList = numberStrList.stream()
                .map(Integer::parseInt)
                .reduce(0, (x, y) -> x + y);
        System.out.println("sumOfNumberStrList = " + sumOfNumberStrList);

        // 위 결과와 동일. 많이 쓰지 않는다. 알아만 두자
        int sumOfNumberStrList2 = numberStrList.stream()
                .reduce(0, (number, str) -> number + Integer.parseInt(str), (num1, num2) -> num1 + num2);
        System.out.println("sumOfNumberStrList2 = " + sumOfNumberStrList2);

        // 예제 - User
        // User
        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setFriendUserIds(Arrays.asList(201, 202, 203, 204));
        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setFriendUserIds(Arrays.asList(204, 205, 206));
        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setFriendUserIds(Arrays.asList(204, 205, 207));

        List<User> users = Arrays.asList(user1, user2, user3);

        int sumOfNumberOfFriends = users.stream()
                .map(User::getFriendUserIds)
                .map(List::size)
                .reduce(0, (x, y) -> x + y);
        System.out.println("sumOfNumberOfFriends = " + sumOfNumberOfFriends);

        // 예제 - Order
        Order order1 = new Order()
                .setId(1001L)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(2000))
                        ));
        Order order2 = new Order()
                .setId(1002L)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(2000)),
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(3000))
                ));
        Order order3 = new Order()
                .setId(1002L)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(2000))
                ));
        List<Order> orders = Arrays.asList(order1, order2, order3);

        // TODO: find the sum of amounts
        BigDecimal reduce = orders.stream()
                .map(Order::getOrderLines)  // Stream<List<OrderLine>>
                .flatMap(List::stream)      // Stream<OrderLine>
                .map(OrderLine::getAmount)  // Stream<BigDecimal>
                .reduce(BigDecimal.ZERO, BigDecimal::add);  // .reduce(BigDecimal.ZERO, (x, y) -> x.add(y));
        System.out.println("reduce = " + reduce);
    }
}