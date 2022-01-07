package com.fastcampus.functionalprogramming.chapter3.util;

@FunctionalInterface	// 단 하나의 메서드만 갖는다는 것을 의미하는 어노테이션
// 매개 변수 3개 이상을 받는 람다식이 없으므로 직접 인터페이스를 만들어보자
public interface TriFunction<T, U, V, R> {
	R apply(T t, U u, V v);
}
