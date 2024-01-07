package com.streams;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class OptionalExample {

	public static void main(String[] args) {
		Car car = new Car();
		Optional<Car> optCar = Optional.empty();
		Optional<Car> optCar1 = Optional.of(car);
		Optional<Car> optCar2 = Optional.ofNullable(car);

		Insurance insurance = new Insurance();
		String name1 = null;
		if (insurance != null) {
			name1 = insurance.getName();
		}
		Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
		Optional<String> name = optInsurance.map(Insurance::getName);

		Person person = new Person();
		Optional<Person> optPerson = Optional.of(person);
//		Optional<String> name2 =
//		optPerson.map(Person::getCar)
//		.map(Car::getInsurance)
//		.map(Insurance::getName);
		String name2 = optPerson.flatMap(Person::getCar).flatMap(Car::getInsurance).map(Insurance::getName)
				.orElse("Unknown");

		Insurance insurance1 = new Insurance();
		if (insurance1 != null && "CambridgeInsurance".equals(insurance1.getName())) {
			System.out.println("ok");
		}
		Optional<Insurance> optInsurance1 = Optional.empty();
		optInsurance.filter(insurance2 -> "CambridgeInsurance".equals(insurance2.getName()))
				.ifPresent(x -> System.out.println("ok"));

//		Object value = map.get("key");
//		Optional<Object> value = Optional.ofNullable(map.get("key"));

		Properties props = new Properties();
		props.setProperty("a", "5");
		props.setProperty("b", "true");
		props.setProperty("c", "-3");

//		assertEquals(5, readDuration(param, "a"));
//		assertEquals(0, readDuration(param, "b"));
//		assertEquals(0, readDuration(param, "c"));
//		assertEquals(0, readDuration(param, "d"));

//		List<Car> cleanCars = dirtyCars.stream()
//				.filter(Car::isClean)
//				.collect(Collectors.toList());

		Map<String, Integer> map = new HashMap<>();
		Integer count = map.getOrDefault("Aston Martin", 0);
		
		ConcurrentHashMap<String, Integer> map1 = new ConcurrentHashMap<>();
		Optional<Integer> maxValue =
		Optional.of(map1.reduceValues(1, Integer::max));
		
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
//		below forEach does not work
//		numbers.stream().forEach(x -> {
//			x = x * 2;
//		});
		
//		numbers.stream().map(x -> x * 2).collect(Collectors.toList());
		numbers.replaceAll(x -> x * 2);
		System.out.println(numbers);
		
		int[] evenNumbers = new int[10];
		Arrays.setAll(evenNumbers, i -> i * 2);

	}

	public Insurance findCheapestInsurance(Person person, Car car) {
		// queries services provided by the different insurance companies
		// compare all those data
		Insurance cheapestCompany = new Insurance();
		return cheapestCompany;
	}

	public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
		if (person.isPresent() && car.isPresent()) {
			return Optional.of(findCheapestInsurance(person.get(), car.get()));
		} else {
			return Optional.empty();
		}
	}

	public Optional<Insurance> nullSafeFindCheapestInsurance1(Optional<Person> person, Optional<Car> car) {
		return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
	}

	public String getCarInsuranceName(Optional<Person> person, int minAge) {
		return person.filter(p -> p.getAge() >= minAge).flatMap(Person::getCar).flatMap(Car::getInsurance)
				.map(Insurance::getName).orElse("Unknown");
	}

	public static Optional<Integer> stringToInt(String s) {
		try {
			return Optional.of(Integer.parseInt(s));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}

	public int readDuration(Properties props, String name) {
		String value = props.getProperty(name);
		if (value != null) {
			try {
				int i = Integer.parseInt(value);
				if (i > 0) {
					return i;
				}
			} catch (NumberFormatException nfe) {

			}
		}
		return 0;
	}

//	public String getData(String url) {
//		return cache.computeIfAbsent(url, this::getData);
//	}

//	public int readDuration(Properties props, String name) {
//		return Optional.ofNullable(props.getProperty(name)).flatMap(OptionalUtility::stringToInt).filter(i -> i > 0)
//				.orElse(0);
//	}

}
