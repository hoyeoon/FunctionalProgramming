package com.fastcampus.functionalprogramming.chapter5;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

import com.fastcampus.functionalprogramming.chapter5.model.User;

public class Chapter5Section2 {
	public static void main(String[] args) {

		/** ClassName::InstanceMethodName
		 * 해당 클래스의 인스턴스를 매개변수(parameter)로 넘겨 메서드를 실행해주는 함수
		 */

		// 예제 1
		Function<String, Integer> strLength = String::length;
		int length = strLength.apply("hello world!");
		System.out.println(length);

		// 예제 2
		BiPredicate<String, String> strEquals = String::equals;
		boolean helloEqualsWorld = strEquals.test("hello", "hello");
		System.out.println(helloEqualsWorld);

		// 예제 3
		List<User> users = new ArrayList<>();
		users.add(new User(3, "Alice"));
		users.add(new User(1, "Charlie"));
		users.add(new User(5, "Bob"));

//		printUserField(users, (User user) -> user.getId());
		printUserField(users, User::getId);
		printUserField(users, User::getName);
	}

	public static void printUserField(List<User> users, Function<User, Object> getter) {
		for(User user : users) {
			System.out.println(getter.apply(user));
		}
	}
}