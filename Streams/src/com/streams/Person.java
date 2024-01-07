package com.streams;

import java.util.Optional;

public class Person {

	private Optional<Car> car;
	
	private Car car1;
	
	private int age;

	public Optional<Car> getCar() {
		return car;
	}

	public Optional<Car> getCarAsOptional() {
		return Optional.ofNullable(car1);
	}

	public int getAge() {
		return age;
	}

}
