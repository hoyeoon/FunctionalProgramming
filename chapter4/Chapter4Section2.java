package com.fastcampus.functionalprogramming.chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Chapter4Section2 {
	public static void main(String[] args) {
//		Consumer<String> myStringConsumer = str -> 	System.out.println(str);
//		myStringConsumer.accept("hello");

		List<Integer> integerInputs = Arrays.asList(4, 2, 3);
		List<Double> doubleInputs = Arrays.asList(1.1, 2.2, 3.3);

		Consumer<Integer> myIntegerProcessor = x -> {
			System.out.println("Processing Integer : " + x);
		};

		Consumer<Integer> myDifferentIntegerProcessor = x -> {
			System.out.println("Processing Integer in different way : " + x);
		};

		Consumer<Double> myDoubleProcessor = x -> {
			System.out.println("Processing Double : " + x);
		};

		process(integerInputs, myIntegerProcessor);
		process(integerInputs, myDifferentIntegerProcessor);
		process(doubleInputs, myDoubleProcessor);
	}

	private static <T> void process(List<T> inputs, Consumer<T> processor) {
		for(T input : inputs) {
			processor.accept(input);
		}
	}
}