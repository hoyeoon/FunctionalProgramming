package chapter6;

import chapter6.model.Order;
import chapter6.model.OrderLine;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * FlatMap - 스트림의 스트림을 납작하게
 * - Map + Flatten
 * - 데이터에 함수를 적용한 후 중첩된 stream을 연결하여 하나의 stream으로 리턴
 */
public class Chapter6Section7 {
	public static void main(String[] args) {
		// 예제 1 - 기본 사용법
		String[][] cities = new String[][] {
				{"Seoul", "Busan"},
				{"San Francisco", "New York"},
				{"Madrid", "Barcelona"}
		};
		Stream<String[]> cityStream = Arrays.stream(cities);
		Stream<Stream<String>> cityStreamStream = cityStream.map(x -> Arrays.stream(x));
		List<Stream<String>> cityStreamList = cityStreamStream.collect(toList());	// 이걸 원한 건 아닐 것이다!!

		Stream<String[]> cityStream2 = Arrays.stream(cities);
		Stream<String> flattenedCityStream = cityStream2.flatMap(x -> Arrays.stream(x));
		List<String> flattenedCityList = flattenedCityStream.collect(toList());
		System.out.println("flattenedCityList = " + flattenedCityList);

		// 예제 2 - Order, OrderLine
		Order order1 = new Order()
				.setId(1001)
				.setOrderLines(Arrays.asList(
						new OrderLine()
								.setId(10001)
								.setType(OrderLine.OrderLineType.PURCHASE)
								.setAmount(BigDecimal.valueOf(5000)),
						new OrderLine()
								.setId(10002)
								.setType(OrderLine.OrderLineType.PURCHASE)
								.setAmount(BigDecimal.valueOf(4000))
				));
		Order order2 = new Order()
				.setId(1002)
				.setOrderLines(Arrays.asList(
						new OrderLine()
								.setId(10003)
								.setType(OrderLine.OrderLineType.PURCHASE)
								.setAmount(BigDecimal.valueOf(2000)),
						new OrderLine()
								.setId(10004)
								.setType(OrderLine.OrderLineType.DISCOUNT)
								.setAmount(BigDecimal.valueOf(-1000))
				));
		Order order3 = new Order()
				.setId(1003)
				.setOrderLines(Arrays.asList(
						new OrderLine()
								.setId(10005)
								.setType(OrderLine.OrderLineType.PURCHASE)
								.setAmount(BigDecimal.valueOf(2000))
				));

		// User가 Cart를 여러개 만들어 놨는데, Cart 3개를 합쳐서 한번에 주문을 하기 위해 하나로 만들어야 할 경우가 있다.
		List<Order> orders = Arrays.asList(order1, order2, order3);
		List<OrderLine> mergeOrderLines = orders.stream()	// Stream<Order>
			.map(Order::getOrderLines)						// Stream<List<OrderLine>>
			.flatMap(List::stream)							// Stream<OrderLine> (map의 경우는 Stream<Stream<OrderLine>>)
			.collect(toList());
		System.out.println("mergeOrderLines = " + mergeOrderLines);
	}
}