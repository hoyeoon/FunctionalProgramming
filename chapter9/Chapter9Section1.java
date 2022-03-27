package chapter9;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Scope(스코프/유효범위) - 변수에 접근할 수 있는 범위
 * - 함수 안에 함수가 있을 때 내부 함수에서 외부 함수에 있는 변수에 접근이 가능하다 (lexical scope). 그 반대는 불가능하다.
 *
 * Closure
 * - 내부 함수가 존재하는 한 내부 함수가 사용한 외부 함수의 변수들 역시 계속 존재한다.
 * - 이렇게 lexical scope를 포함하는 함수를 closure라고 한다.
 * - 이 때 내부 함수가 사용한 외부 함수의 변수들은 내부 함수 선언 당시로부터 변할 수 없기 때문에 final로 선언되지 않더라도 암묵적으로 final 취급된다.
 *
 * Curry (Closure 응용)
 * - 여러 개의 매개변수를 받는 함수를 중첩된 여러 함수로 쪼개어 매개 변수를 한 번에 받지 않고 여러 단계에 걸쳐 나눠 받을 수 있게 하는 기술
 */
public class Chapter9Section1 {

    public static void main(String[] args) {
        Supplier<String> supplier = getStringSupplier();
        System.out.println("supplier.get() = " + supplier.get());

        BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;
        Function<Integer, Function<Integer, Integer>> curriedAdd = x -> y -> x + y; // x -> (y -> x + y);

        Function<Integer, Integer> addThree = curriedAdd.apply(3);
        int result = addThree.apply(10);
        System.out.println("result = " + result);
    }

    // Closure
    public static Supplier<String> getStringSupplier() {
        String hello = "Hello";
        Supplier<String> supplier = () -> {
          String world = "World";
          return hello + world;
        };

        return supplier;
    }
}
