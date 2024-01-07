package com.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamExamples {

	public static void main(String[] args) {

//		convert int array to list Integer
		int[] arr = { 1, 2, 3 };
		List<Integer> list = Arrays.stream(arr) // IntStream , LongStream, DoubleStream
				.boxed() // Stream<Integer> returns stream of Wrappers
				.collect(Collectors.toList());

		List<Integer> secondList = IntStream.of(arr) // return Intstream
				.boxed() // Stream<Integer>
				.collect(Collectors.toList());

//		Convert array String to array of int
		String input = "1 2 3 4 5";
		String[] splits = input.split(" ");
//		convert intStream to array int
//		mapToInt/ Long/ Double gives a primitive stream
		int[] result = Arrays.stream(splits).mapToInt(Integer::parseInt).toArray();

//		Converting char array to List char
		String myString = new String("Samir");
		myString.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

//		Ignore Below
		String[] answer = Arrays.stream(arr).sorted().mapToObj(String::valueOf).toArray(String[]::new);

	}

}
