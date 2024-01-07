package com.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import static java.util.Comparator.comparing;

public class AppleExample {

	public static void main(String[] args) {
		List<Apple> inventory = Arrays.asList(new Apple(30, "green"), new Apple(140, "red"), new Apple(90, "brown"),
				new Apple(160, "green"), new Apple(100, "red"), new Apple(300, "brown"));

		Collections.sort(inventory, new Comparator<Apple>() {
			public int compare(Apple a1, Apple a2) {
				return a1.getWeight().compareTo(a2.getWeight());
			}
		});
		inventory.sort(Comparator.comparing(Apple::getWeight));
		inventory.sort(comparing(Apple::getWeight).reversed());
		inventory.sort(comparing(Apple::getWeight)
				.reversed()
				.thenComparing(Apple::getCountry));
		
		inventory.sort(
				(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
		inventory.sort(comparing(Apple::getWeight));

//		filterApples(inventory, Apple::isGreenApple);
//		filterApples(inventory, Apple::isHeavyApple);

		filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
		filterApples(inventory, (Apple a) -> a.getWeight() > 150);
		filterApples(inventory, (Apple a) -> a.getWeight() < 80 || "brown".equals(a.getColor()));

		filterApples(inventory, (Apple a) -> a.getWeight() > 150);
//		filter(inventory, (Apple a) -> a.getWeight() > 150);
		List<Apple> heavyApples = inventory.stream().filter((Apple a) -> a.getWeight() > 150)
				.collect(Collectors.toList());
		List<Apple> heavyApples1 = inventory.parallelStream().filter((Apple a) -> a.getWeight() > 150)
				.collect(Collectors.toList());

		List<Apple> heavyApples2 = inventory.stream().filter((Apple a) -> a.getWeight() > 150)
				.collect(Collectors.toList());
		List<Apple> heavyApples3 = inventory.parallelStream().filter((Apple a) -> a.getWeight() > 150)
				.collect(Collectors.toList());

		List<Apple> greenApples = filterApplesByColor(inventory, "green");
		List<Apple> redApples = filterApplesByColor(inventory, "red");

//		List<Apple> greenApples1 = filterApples(inventory, "green", 0, true);
//		List<Apple> heavyApples4 = filterApples(inventory, "", 150, false);

	}

	public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getColor().equals(color)) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getWeight() > weight) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}

	public interface ApplePredicate {
		boolean test(Apple apple);
	}

	public class AppleRedAndHeavyPredicate implements ApplePredicate {
		public boolean test(Apple apple) {
			return "red".equals(apple.getColor()) && apple.getWeight() > 150;
		}
	}

}
