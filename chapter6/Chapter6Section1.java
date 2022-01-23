package chapter6;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream
 * - 데이터의 흐름
 * - 컬렉션(Collection) 형태로 구성된 데이터를 람다를 이용해 간결하고 직관적으로 프로세스하게 해줌
 * - for, while 등을 이용하던 기존 loop을 대체
 * - 손쉽게 병렬 처리를 할 수 있게 해줌
 */
public class Chapter6Section1 {
	public static void main(String[] args) {
		Stream<String> nameStream = Stream.of("Alice", "Bob", "Charlie");
		List<String> names = nameStream.collect(Collectors.toList());
		System.out.println(names);

		String[] cityArray = new String[] {"San Jose", "Seoul", "Tokyo"};
		Stream<String> cityStream = Arrays.stream(cityArray);
		List<String> cityList = cityStream.collect(Collectors.toList());
		System.out.println(cityList);

		Set<Integer> numberSet = new HashSet<>(Arrays.asList(3, 5, 7));
		Stream<Integer> numberStream = numberSet.stream();
		List<Integer> numberList = numberStream.collect(Collectors.toList());
		System.out.println(numberList);
	}
}
