package chapter8;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Collectors
 * - toList(), toSet(), mapping(~~), reducing(~~)
 */
public class Chapter8Section5 {
    public static void main(String[] args) {
        // 예제 - 기본 List
        List<Integer> numberList = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.toList());
        System.out.println("numberList = " + numberList);

        // 예제 - Set
        Set<Integer> numberSet = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.toSet());
        System.out.println("numberSet = " + numberSet);

        // 예제 - mapping는 map과 동일한 효과
        List<Integer> numberList2 = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.mapping(x -> Math.abs(x), Collectors.toList()));
        System.out.println("numberList2 = " + numberList2);

        Set<Integer> numberSet2 = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.mapping(x -> Math.abs(x), Collectors.toSet()));
        System.out.println("numberSet2 = " + numberSet2);

        // 예제 - reducing은 reduce와 동일한 효과
        int sum = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.reducing(0, (x, y) -> x + y));
        System.out.println("sum = " + sum);
    }
}