package chapter6;

import chapter6.model.Order;
import chapter6.model.User;

import java.util.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.stream.Collectors;

import static chapter6.model.Order.OrderStatus.ERROR;
import static java.util.stream.Collectors.toList;

/**
 * 중복제거
 * - 중복되는 데이터가 제거된 stream을 리턴
 */
public class Chapter6Section6 {
	public static void main(String[] args) {
		// 예제 1 - 기본 사용법
		List<Integer> numbers = Arrays.asList(3, -5, 4, -5, 2, 3);
		List<Integer> distinctNumbers = numbers.stream()
				.distinct()
				.collect(toList());
		System.out.println("distinctNumbers = " + distinctNumbers);

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

		// TODO: created a sorted list of unique CreatedByUserIds from the orders
		List<Long> userIds = orders.stream()
				.map(Order::getCreatedByUserId) //.map(order -> order.getCreatedByUserId())
				.distinct()
				.sorted()
				.collect(toList());
		System.out.println("userIds = " + userIds);
	}
}