package FunctionalProgramming.chapter3;

import java.util.function.Function;
import chapter3.util.Adder;

/**
 * Integer 타입을 매개변수로 받아 Integer 타입으로 반환하는 함수를 Function 인터페이스를 object 형태로 구현하여 사용
 */
public class Chapter3Section1 {

	public static void main(String[] args) {
		Function<Integer, Integer> myAdder = new Adder();
		int result = myAdder.apply(5);
		System.out.println(result);
	}
}
