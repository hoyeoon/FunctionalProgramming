package chapter6;

import chapter6.model.Order;
import chapter6.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static chapter6.model.Order.OrderStatus.ERROR;
import static java.util.stream.Collectors.toList;

/**
 * Map
 * - 데이터를 변형하는데 사용
 * - 데이터에 해당 함수가 적용된 결과물을 제공하는 stream을 리턴
 */
public class Chapter6Section3 {
	public static void main(String[] args) {
		// 예제 1 - 기본 사용법
		List<Integer> numberList = Arrays.asList(3, 6, -4);
		List<Integer> numberStreamX2 = numberList.stream()
				.map(x -> x * 2)
				.collect(toList());
		System.out.println("numberStreamX2 = " + numberStreamX2);

		// 예제 2 - 기본 사용법
		Stream<Integer> numberListStream = numberList.stream();
		Stream<String> stringStream = numberListStream.map(x -> "Number is " + x);
		List<String> stringList = stringStream.collect(toList());
		System.out.println("stringList = " + stringList);

		// 예제 3 - User
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

		// TODO: Create list of emailAddress
		List<String> emailAddress = users.stream()
				.map(User::getEmailAddress)	// map(user -> user.getEmailAddress());
				.collect(toList());
		System.out.println("emailAddress = " + emailAddress);

		// 예제 4 - Order
		Order order1 = new Order()
				.setId(1001)
				.setStatus(Order.OrderStatus.CREATED)
				.setCreatedByUserId(101);
		Order order2 = new Order()
				.setId(1002)
				.setStatus(ERROR)
				.setCreatedByUserId(102);
		Order order3 = new Order()
				.setId(1003)
				.setStatus(Order.OrderStatus.PROCESSED)
				.setCreatedByUserId(103);
		Order order4 = new Order()
				.setId(1004)
				.setStatus(ERROR)
				.setCreatedByUserId(104);
		Order order5 = new Order()
				.setId(1005)
				.setStatus(Order.OrderStatus.IN_PROGRESS)
				.setCreatedByUserId(105);

		List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);

		// TODO: Create list of createdByUserId
		List<Long> createdByUserIds = orders.stream()
				.map(Order::getCreatedByUserId)
				.collect(toList());
		System.out.println("createdByUserIds = " + createdByUserIds);
	}
}
