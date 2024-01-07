package com.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.streams.Dish.CaloricLevel;

public class MenuExample {

	public static void main(String[] args) {

		List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT), new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER), new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER), new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH));
		
		Stream<Dish> menuStream = menu.stream();
		
		List<String> names = menu.stream()
				.filter(d -> {
					System.out.println("filtering" + d.getName());
					return d.getCalories() > 300;
				})
				.map(d -> {
					System.out.println("mapping" + d.getName());
					return d.getName();
				})
				.limit(3)
				.collect(Collectors.toList());
		System.out.println(names);

		List<String> lowCaloricDishesName = menu.parallelStream().filter(d -> d.getCalories() < 400)
				.sorted(Comparator.comparing(Dish::getCalories)).map(Dish::getName).limit(3).collect(Collectors.toList());

		Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(Collectors.groupingBy(Dish::getType));

		menu.stream().forEach(System.out::println);

		long count = menu.stream().filter(d -> d.getCalories() > 300).distinct().limit(3).count();

		List<Dish> vegetarianDishes = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());

		List<Dish> dishes = menu.stream().filter(d -> d.getCalories() > 300).limit(3).collect(Collectors.toList());

		List<Dish> dishes1 = menu.stream().filter(d -> d.getCalories() > 300).skip(2).collect(Collectors.toList());

		List<Dish> dishes3 = menu.stream().filter(d -> d.getType() == Dish.Type.MEAT).limit(2)
				.collect(Collectors.toList());

		List<String> dishNames = menu.stream().map(Dish::getName).collect(Collectors.toList());

		List<Integer> dishNameLengths = menu.stream().map(Dish::getName).map(String::length)
				.collect(Collectors.toList());

		if (menu.stream().anyMatch(Dish::isVegetarian)) {
			System.out.println("The menu is (somewhat) vegetarian friendly!!");
		}
		boolean isHealthy = menu.stream().allMatch(d -> d.getCalories() < 1000);

		boolean isHealthy1 = menu.stream().noneMatch(d -> d.getCalories() >= 1000);

		Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
		menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(d -> System.out.println(d.getName()));
		
		int count1 = menu.stream().map(d -> 1).reduce(0, (a, b) -> a + b);

		int calories = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);

//		wrong
//		int calories1 = menu.stream()
//				.map(Dish::getCalories)
//				.sum();

		OptionalInt maxCalories = menu.stream().mapToInt(Dish::getCalories).max();
		int max = maxCalories.orElse(1);

		long howManyDishes = menu.stream().collect(Collectors.counting());
		long howManyDishes1 = menu.stream().count();

		Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
		Optional<Dish> mostCalorieDish = menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));

		int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));

		double avgCalories = menu.stream().collect(Collectors.averagingInt(Dish::getCalories));

		IntSummaryStatistics menuStatistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));

//		Output IntSummaryStatistics{count=9, sum=4300, min=120,
//		average=477.777778, max=800}

		String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining());
//		String shortMenu1 = menu.stream().collect(Collectors.joining());
		String shortMenu2 = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
		int totalCalories1 = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));

		Optional<Dish> mostCalorieDish1 = menu.stream()
				.collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));

		int totalCalories2 = menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();

		int totalCalories3 = menu.stream().mapToInt(Dish::getCalories).sum();
		
		IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
		Stream<Integer> stream = intStream.boxed();

		String shortMenu3 = menu.stream().map(Dish::getName).collect(Collectors.joining());
		String shortMenu4 = menu.stream().map(Dish::getName).collect(Collectors.reducing((s1, s2) -> s1 + s2)).get();
//		String shortMenu5 = menu.stream()
//		.collect( Collectors.reducing( (d1, d2) -> d1.getName() + d2.getName() ) ).get();
		String shortMenu6 = menu.stream().collect(Collectors.reducing("", Dish::getName, (s1, s2) -> s1 + s2));

		Map<Dish.Type, List<Dish>> dishesByType1 = menu.stream().collect(Collectors.groupingBy(Dish::getType));
//		Check why dish does not work dish1
		Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(Collectors.groupingBy(dish1 -> {
			if (dish1.getCalories() <= 400)
				return CaloricLevel.DIET;
			else if (dish1.getCalories() <= 700)
				return CaloricLevel.NORMAL;
			else
				return CaloricLevel.FAT;
		}));

		Map<Dish.Type, Long> typesCount = menu.stream()
				.collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));

		Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream()
				.collect(Collectors.groupingBy(Dish::getType, Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));

		Map<Dish.Type, Integer> totalCaloriesByType = menu.stream()
				.collect(Collectors.groupingBy(Dish::getType, Collectors.summingInt(Dish::getCalories)));

		Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream()
				.collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(dish1 -> {
					if (dish1.getCalories() <= 400)
						return CaloricLevel.DIET;
					else if (dish1.getCalories() <= 700)
						return CaloricLevel.NORMAL;
					else
						return CaloricLevel.FAT;
				}, Collectors.toSet())));

		Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType1 = menu.stream()
				.collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(dish1 -> {
					if (dish1.getCalories() <= 400)
						return CaloricLevel.DIET;
					else if (dish1.getCalories() <= 700)
						return CaloricLevel.NORMAL;
					else
						return CaloricLevel.FAT;
				}, Collectors.toCollection(HashSet::new))));
		
		Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream()
				.collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish2 -> {
					if (dish2.getCalories() <= 400)
						return CaloricLevel.DIET;
					else if (dish2.getCalories() <= 700)
						return CaloricLevel.NORMAL;
					else
						return CaloricLevel.FAT;
				})));
		
		Map<CaloricLevel, List<Dish>> dishesByCaloricLevel1 = menu.stream()
				.collect(Collectors.groupingBy(Dish::getCaloricLevel));

//		List<Dish> vegetarianDishes1 = partitionedMenu.get(true);
		Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
		Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType =
				menuStream.collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));
		List<Dish> vegetarianDishes2 = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
		
		Map<Dish.Type, Dish> mostCalocricByType = menuStream.collect(Collectors.groupingBy(Dish::getType,
				Collectors.collectingAndThen(
						Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
						Optional::get)));

		Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menu.stream().collect(Collectors.partitioningBy(
				Dish::isVegetarian,
				Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));

		menu.stream().collect(
				Collectors.partitioningBy(Dish::isVegetarian, Collectors.partitioningBy(d -> d.getCalories() > 500)));
//		menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.partitioningBy(Dish::getType)));
		menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.counting()));
		
		List<Dish> dishes2 = menuStream.collect(Collectors.toList());
		Set<Dish> dishes4 = menuStream.collect(Collectors.toSet());
//		Collection<Dish> dishes5 = menuStream.collect(Collectors.toCollection(), ArrayList::new);
		long howManyDishes2 = menuStream.collect(Collectors.counting());
		int totalCalories4 = menuStream.collect(Collectors.summingInt(Dish::getCalories));
		double avgCalories1 = menuStream.collect(Collectors.averagingInt(Dish::getCalories));
		IntSummaryStatistics menuStatistics1 =
				menuStream.collect(Collectors.summarizingInt(Dish::getCalories));
		String shortMenu7 = menuStream.map(Dish::getName).collect(Collectors.joining(", "));
		Optional<Dish> fattest = menuStream.collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));
		Optional<Dish> lightest = menuStream.collect(Collectors.minBy(Comparator.comparingInt(Dish::getCalories)));
		int totalCalories5 = menuStream.collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
		int howManyDishes3 = menuStream.collect(Collectors.collectingAndThen(Collectors.toList(), List::size));
		Map<Dish.Type, List<Dish>> dishesByType2 =
				menuStream.collect(Collectors.groupingBy(Dish::getType));
		Map<Boolean, List<Dish>> vegetarianDishes1 =
				menuStream.collect(Collectors.partitioningBy(Dish::isVegetarian));
		List<Dish> dishes5 = menuStream.collect(
				ArrayList::new,
				List::add,
				List::addAll);
		
		int totalCalories6 =
				menu.stream().map(Dish::getCalories)
				.reduce(0, (c1, c2) -> c1 + c2);
		menu.parallelStream()
		.filter(d -> d.getCalories() > 400)
		.map(Dish::getName)
		.collect(Collectors.toList());
		
//		List<Dish> dishes6 = menuStream.collect(new ToListCollector<Dish>());

	}

}
