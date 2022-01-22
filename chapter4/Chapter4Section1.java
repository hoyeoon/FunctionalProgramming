package chapter4;

import java.util.function.Supplier;

public class Chapter4Section1 {
	public static void main(String[] args) {
		/**
		 * Supplier는 아무런 input parameter없이 output을 return 할 때 사용하는 functional interface
		 */
		Supplier<String> myStringSupplier = () -> "hello world!";
		System.out.println(myStringSupplier.get());

		Supplier<Double> myRandomDoubleSupplier = () -> Math.random();
		printRandomDoubles(myRandomDoubleSupplier, 5);
	}

	private static void printRandomDoubles(Supplier<Double> randomSupplier, int count) {
		for(int i = 0; i < count; i++) {
			System.out.println(randomSupplier.get());
		}
	}
}