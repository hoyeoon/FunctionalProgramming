package com.fastcampus.functionalprogramming.chapter3;

import java.util.function.Function;

public class Chapter3Section2 {

	/**
	 * 람다식(익명 함수)을 사용하면 Function 인터페이스를 object 형태로 클래스를 구현할 필요 없이 메서드 내에서 단순하게 구현 가능
	 */
	public static void main(String[] args) {
//		Function<Integer, Integer> myAdder = (Integer x) -> {
//			return x + 10;
//		};
		Function<Integer, Integer> myAdder = x -> x + 10;
		int result = myAdder.apply(5);
		System.out.println(result);
	}
}
