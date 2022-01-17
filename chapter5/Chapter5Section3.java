package com.fastcampus.functionalprogramming.chapter5;

import java.util.function.BiFunction;

import com.fastcampus.functionalprogramming.chapter5.model.User;

public class Chapter5Section3 {
	public static void main(String[] args) {
		User user = new User(1, "Alice");
//		BiFunction<Integer, String, User> userCreator = (Integer id, String name) -> new User(id, name);
		BiFunction<Integer, String, User> userCreator = User::new;
		User charlie = userCreator.apply(3, "Charlie");
		System.out.println(charlie);
	}
}