package chapter9;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Lazy Evaluation
 * - Lambda의 계산은 계산값이 필요할 때가 되어서야 계산된다.
 * - 이를 이용하여 불필요한 계산을 줄이거나 해당 코드의 실행 순서를 의도적으로 미룰 수 있다.
 */
public class Chapter9Section2 {

    public static void main(String[] args) {
        if(true || returnFalse()) {
            System.out.println("true");
        }

        System.out.println("-----------------------------");

        if(or(returnTrue(), returnFalse())){
            System.out.println("true");
        }

        System.out.println("-----------------------------");

        if(lazyOr(() -> returnTrue(), () -> returnFalse())) {
            System.out.println("true");
        }

        System.out.println("-----------------------------");

        /**
         * Stream은 종결처리(ex. collect)가 이루어지기 전까지 모든 계산을 미룬다.(Lazy Evaluation)
         * 따라서, 아래 결과에서 peeking이 먼저 나올 것 같지만, 그렇지 않다.
         */
        Stream<Integer> integerStream = Stream.of(3, -2, 5, 8, -3, 10)
                .filter(x -> x > 0)
                .peek(x -> System.out.println("peeking " + x))
                .filter(x -> x % 2 == 0);
        System.out.println("Before collect");

        List<Integer> integers = integerStream.collect(Collectors.toList());
        System.out.println("After collect: " + integers);
    }

    public static boolean lazyOr(Supplier<Boolean> x, Supplier<Boolean> y) {
        return x.get() || y.get();
    }

    public static boolean or(boolean x, boolean y) {
        return x || y;
    }

    public static boolean returnTrue() {
        System.out.println("Returning true");
        return true;
    }

    public static boolean returnFalse() {
        System.out.println("Returning false");
        return false;
    }
}