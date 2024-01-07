package com.streams;

public class Dish {

	private final String name;
	private final boolean vegetarian;
	private final int calories;
	private final Type type;

	public String getName() {
		return name;
	}

	public Dish(String name, boolean vegetarian, int calories, Type type) {
		super();
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public int getCalories() {
		return calories;
	}

	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return name;
	}

	public enum Type {
		MEAT, FISH, OTHER
	}

	public enum CaloricLevel {
		DIET, NORMAL, FAT
	}

	public CaloricLevel getCaloricLevel() {
		if (this.getCalories() <= 400)
			return CaloricLevel.DIET;
		else if (this.getCalories() <= 700)
			return CaloricLevel.NORMAL;
		else
			return CaloricLevel.FAT;
	}

}
