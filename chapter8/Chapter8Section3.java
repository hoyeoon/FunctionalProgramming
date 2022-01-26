package chapter8;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * findFirst
 * - Stream 안의 첫번째 데이터를 리턴. Stream이 비어있다면, 비어있는 Optional을 반환
 *
 * findAny
 * - Stream 안의 아무 데이터나 리턴. 순서가 중요하지 않고 Parallel Stream을 사용할 때 최적화 할 수 있다.
 * - 마찬가지로 Stream이 비어 있다면, 비어있는 Optional을 반환
 */
public class Chapter8Section3 {
    public static void main(String[] args) {
        Optional<Integer> anyNegativeInteger = Stream.of(3, 2, -5, 6)
                .filter(x -> x < 0)
                .findAny();
        System.out.println("anyNegativeInteger = " + anyNegativeInteger.orElse(0));

        Optional<Integer> firstPositiveInteger = Stream.of(3, 2, -5, 6)
                .filter(x -> x > 0)
                .findFirst();
        System.out.println("firstPositiveInteger = " + firstPositiveInteger.orElse(0));
    }
}