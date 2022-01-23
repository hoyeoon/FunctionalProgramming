package chapter6;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static chapter6.model.Order.OrderStatus.ERROR;
import static java.util.stream.Collectors.*;

import chapter6.model.*;

/**
 * Filter
 * - 만족하는 데이터만 걸러내는데 사용
 * - Predicate에 true를 반환하는 데이터만 존재하는 stream을 리턴
 *
 */
public class Chapter6Section2 {
	public static void main(String[] args) {
		// 예제 1 - 기본 사용법
/*		Stream<Integer> numberStream = Stream.of(3, -5, 7, 10, -3);
		Stream<Integer> filteredNumberStream = numberStream.filter(x -> x > 0);
		List<Integer> filteredNumbers = filteredNumberStream.collect(Collectors.toList());
		System.out.println(filteredNumbers);*/

		List<Integer> newFilteredNumbers = Stream.of(3, -5, 7, 10, -3)
				.filter(x -> x > 0)
				.collect(toList());
		System.out.println(newFilteredNumbers);

		// 예제 2 - User
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
				.setVerified(true)
				.setEmailAddress("charlie@gmail.com");

		List<User> users = Arrays.asList(user1, user2, user3);
		List<User> verifiedUsers = users.stream()
				.filter(User::isVerified)	// .filter(user -> user.isVerified())
				.collect(toList());
		System.out.println("verifiedUsers = " + verifiedUsers);

		List<User> unVerifiedUsers = users.stream()
				.filter(user -> !user.isVerified())
				.collect(toList());
		System.out.println("unVerifiedUsers = " + unVerifiedUsers);

		// 예제 3 - Order
		Order order1 = new Order()
				.setId(1001)
				.setStatus(Order.OrderStatus.CREATED);
		Order order2 = new Order()
				.setId(1002)
				.setStatus(ERROR);
		Order order3 = new Order()
				.setId(1003)
				.setStatus(Order.OrderStatus.PROCESSED);
		Order order4 = new Order()
				.setId(1004)
				.setStatus(ERROR);
		Order order5 = new Order()
				.setId(1005)
				.setStatus(Order.OrderStatus.IN_PROGRESS);

		List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);

		// Filter orders in ERROR state
		Predicate<Order> isError = order -> order.getStatus() == ERROR;

		List<Order> filteredOrders = orders.stream().
				filter(isError).collect(toList());

		System.out.println("filteredOrders = " + filteredOrders);
	}
}
