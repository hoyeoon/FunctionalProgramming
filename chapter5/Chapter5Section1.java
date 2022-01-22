package chapter5;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class Chapter5Section1 {
	public static void main(String[] args) {

		/** ClassName::staticMethodName
		 *  클래스의 static method(정적 메서드)를 지정할 때
		 */
		Function<String, Integer> str2int = Integer::parseInt;
		int result = str2int.apply("20");
		System.out.println(result);

		/** objectName::instanceMethodName
		 *  이미 선언되어있는 객체의 instance method를 지정할 때
		 */
		String str = "hello";
		Predicate<String> equalsToHello = str::equals;
		boolean result2 = equalsToHello.test("hello");
		System.out.println(result2);

		// 예제
		System.out.println(calculate(8, 2, (x, y) -> x + y));
		System.out.println(calculate(8, 2, Chapter5Section1::multiply));

		Chapter5Section1 instance = new Chapter5Section1();
		System.out.println(calculate(8, 2, instance::subtract));
		instance.myMethod();
	}

	public static int calculate(int x, int y, BiFunction<Integer, Integer, Integer> operator) {
		return operator.apply(x, y);
	}

	public static int multiply(int x, int y) {
		return x * y;
	}

	public int subtract(int x, int y) {
		return x - y;
	}

	public void myMethod() {
		System.out.println(calculate(10, 3, this::subtract));
	}
}
