package com.fastcampus.functionalprogramming.chapter3.util;

@FunctionalInterface	// 추상 메서드가 딱 하나만 존재하는 인터페이스를 의미
// 매개 변수 3개 이상을 받는 람다식이 없으므로 직접 인터페이스를 만들어보자
public interface TriFunction<T, U, V, R> {
	R apply(T t, U u, V v);
}
