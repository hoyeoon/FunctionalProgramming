package chapter6;

import chapter6.model.Order;
import chapter6.model.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

import static chapter6.model.Order.OrderStatus.ERROR;
import static java.util.stream.Collectors.toList;

/**
 * 데이터의 정렬
 * - 데이터가 순서대로 정렬된 stream을 리턴
 * - 데이터의 종류에 따라 Comparator가 필요할 수 있음
 */
public class Chapter6Section5 {
	public static void main(String[] args) {
		// 예제 1 - 기본 사용법
		List<Integer> numbers = Arrays.asList(3, -5, 7, 4);
		List<Integer> sortedNumbers = numbers.stream()
				.sorted()
				.collect(toList());
		System.out.println("sortedNumbers = " + sortedNumbers);

		// 예제 2 - User
		User user1 = new User()
				.setId(101)
				.setName("Bob")
				.setVerified(false)
				.setEmailAddress("bob@gmail.com");
		User user2 = new User()
				.setId(102)
				.setName("Charlie")
				.setVerified(false)
				.setEmailAddress("charlie@gmail.com");
		User user3 = new User()
				.setId(103)
				.setName("Alice")
				.setVerified(true)
				.setEmailAddress("alice@gmail.com");

		List<User> users = Arrays.asList(user1, user2, user3);
		List<User> sortedUsers = users.stream()
				.sorted((u1, u2) -> u1.getName().compareTo(u2.getName()))
				.collect(toList());
		System.out.println("sortedUsers = " + sortedUsers);

		// 예제 3 - Order
		LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

		Order order1 = new Order()
				.setId(1001)
				.setStatus(Order.OrderStatus.CREATED)
				.setCreatedByUserId(101)
				.setCreatedAt(now.minusHours(4));
		Order order2 = new Order()
				.setId(1002)
				.setStatus(ERROR)
				.setCreatedByUserId(103)
				.setCreatedAt(now.minusHours(1));
		Order order3 = new Order()
				.setId(1003)
				.setStatus(Order.OrderStatus.PROCESSED)
				.setCreatedByUserId(102)
				.setCreatedAt(now.minusHours(36));
		Order order4 = new Order()
				.setId(1004)
				.setStatus(ERROR)
				.setCreatedByUserId(104)
				.setCreatedAt(now.minusHours(15));
		Order order5 = new Order()
				.setId(1005)
				.setStatus(Order.OrderStatus.IN_PROGRESS)
				.setCreatedByUserId(101)
				.setCreatedAt(now.minusHours(10));

		List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);

		// TODO: sort the orders based on createdAt
		List<Order> sortedOrders = orders.stream()
				.sorted((o1, o2) -> o1.getCreatedAt().compareTo(o2.getCreatedAt()))
				.collect(toList());
		System.out.println("sortedOrders = " + sortedOrders);
	}
}