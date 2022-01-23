package chapter6;

import chapter6.model.Order;
import chapter6.model.User;

import java.time.LocalDateTime;
import java.time.ZoneId; // 어느 지역에서 LocalDateTime 을 적용할 지를 위함
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static chapter6.model.Order.OrderStatus.ERROR;
import static java.util.stream.Collectors.toList;

/**
 * STREAM의 구성 요소
 * - 여러가지의 중간 처리를 이어붙이는 것이 가능
 * 1. Source(소스) - 컬렉션, 배열 등
 * 2. Integermediate Operations(중간 처리) - 0개 이상의 filter, map 등의 중간처리
 * 3. Terminal Operation(종결 처리) - Collect, reduce 등
 */
public class Chapter6Section4 {
	public static void main(String[] args) {
		// 예제 1 - User
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

		// 기존 방식 - how to do
		List<String> emails = new ArrayList<>();
		for (User user : users) {
			if (!user.isVerified()) {
				String email = user.getEmailAddress();
				emails.add(email);
			}
		}
		System.out.println("emails = " + emails);

		// Stream 방식 - what to do 에 집중 (각 과정에서 무엇을 해야하는지 집중)
		List<String> emails2 = users.stream()
				.filter(user -> !user.isVerified())
				.map(User::getEmailAddress)
				.collect(toList());
		System.out.println("emails2 = " + emails2);


		// 예제 2 - Order
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

		// TODO: Find orders in Error status, and extract createdByUserIds as a list
		List<Long> userIds = orders.stream()
				.filter(order -> order.getStatus() == ERROR)
				.map(Order::getCreatedByUserId)
				.collect(toList());
		System.out.println("userIds = " + userIds);

		// TODO: Find orders in ERROR status and created within 24 hours (Hint : now.isAfter, now.minusHours(24))
		List<Order> ordersInErrorStatusIn24hrs = orders.stream()
				.filter(order -> order.getStatus() == ERROR)
				.filter(order -> order.getCreatedAt().isAfter(now.minusHours(24)))
				.collect(toList());
		System.out.println("ordersInErrorStatusIn24hrs = " + ordersInErrorStatusIn24hrs);
	}
}