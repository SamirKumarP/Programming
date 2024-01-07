package com.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MiscStream {

	public static void main(String[] args) {
		int[] numbers0 = {2, 3, 5, 7, 11, 13};
		int sum = Arrays.stream(numbers0).sum();
		
		List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
//		numbers.stream().forEach(x -> x = x*x);
		numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);

		List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Action");
		List<String> uniqueCharacters = words.stream().map(word -> word.split(""))
		.flatMap(Arrays::stream)
		.distinct()
		.collect(Collectors.toList());
		List<Integer> wordLengths = words.stream().map(String::length).collect(Collectors.toList());

		words.stream().map(word -> word.split("")).distinct().collect(Collectors.toList());

		words.stream().map(word -> word.split("")).distinct().collect(Collectors.toList());

		String[] arrayOfWords = { "Goodbye", "World" };
		Stream<String> streamOfwords = Arrays.stream(arrayOfWords);

		List<Integer> numbers3 = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> squares = numbers3.stream().map(n -> n * n).collect(Collectors.toList());

		List<Integer> numbers1 = Arrays.asList(1, 2, 3);
		List<Integer> numbers2 = Arrays.asList(3, 4);
		List<int[]> pairs = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[] { i, j }))
				.collect(Collectors.toList());

		List<Integer> numbers11 = Arrays.asList(1, 2, 3);
		List<Integer> numbers12 = Arrays.asList(3, 4);
		List<int[]> pairs1 = numbers11.stream()
				.flatMap(i -> numbers12.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[] { i, j }))
				.collect(Collectors.toList());

//		The result is [(2, 4), (3, 3)].

		List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
		Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream().map(x -> x * x).filter(x -> x % 3 == 0)
				.findFirst(); // 9

		int sum4 = 0;
		for (int x : numbers) {
			sum4 += x;
		}

		int sum1 = numbers.stream().reduce(0, (a, b) -> a + b);

		int product = numbers.stream().reduce(1, (a, b) -> a * b);

		int sum2 = numbers.stream().reduce(0, Integer::sum);

		Optional<Integer> sum3 = numbers.stream().reduce((a, b) -> (a + b));

		Optional<Integer> max = numbers.stream().reduce(Integer::max);
		Optional<Integer> min = numbers.stream().reduce(Integer::min);

		Stream.generate(Math::random).limit(5).forEach(System.out::println);

		IntStream ones = IntStream.generate(() -> 1);
		IntStream twos = IntStream.generate(new IntSupplier() {
			public int getAsInt() {
				return 2;
			}
		});

		IntSupplier fib = new IntSupplier() {
			private int previous = 0;
			private int current = 1;

			public int getAsInt() {
				int oldPrevious = this.previous;
				int nextValue = this.previous + this.current;
				this.previous = this.current;
				this.current = nextValue;
				return oldPrevious;
			}
		};
		IntStream.generate(fib).limit(10).forEach(System.out::println);

		Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
		stream.map(String::toUpperCase).forEach(System.out::println);
		Stream<String> emptyStream = Stream.empty();

		Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

		Stream<Integer> stream1 = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
		List<Integer> numbers4 = stream1.reduce(new ArrayList<Integer>(), (List<Integer> l, Integer e) -> {
			l.add(e);
			return l;
		}, (List<Integer> l1, List<Integer> l2) -> {
			l1.addAll(l2);
			return l1;
		});
		
		ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
		Optional<Integer> maxValue =
		Optional.of(map.reduceValues(1, Integer::max));
		
	}

	public boolean isPrime(int candidate) {
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
	}

	public Map<Boolean, List<Integer>> partitionPrimes(int n) {
		return IntStream.rangeClosed(2, n).boxed().collect(Collectors.partitioningBy(candidate -> isPrime(candidate)));
	}

}
