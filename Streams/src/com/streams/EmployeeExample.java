package com.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeExample {

	public static void main(String[] args) {
		ArrayList<Employee> employees = getUnsortedEmployeeList();

		// Compare by first name and then last name
		Comparator<Employee> compareByName = Comparator.comparing(Employee::getFirstName)
				.thenComparing(Employee::getLastName);

		List<Employee> sortedEmployees = employees.stream().sorted(compareByName).collect(Collectors.toList());
		sortedEmployees.stream().forEach(se -> System.out.println(se.getFirstName() + se.getLastName()));

		System.out.println(sortedEmployees);
	}

	private static ArrayList<Employee> getUnsortedEmployeeList() {
		ArrayList<Employee> list = new ArrayList<>();
		list.add(new Employee(2l, "Lokesh", "Gupta"));
		list.add(new Employee(1l, "Alex", "Gussin"));
		list.add(new Employee(4l, "Brian", "Sux"));
		list.add(new Employee(5l, "Neon", "Piper"));
		list.add(new Employee(3l, "David", "Beckham"));
		list.add(new Employee(7l, "Alex", "Beckham"));
		list.add(new Employee(6l, "Brian", "Suxena"));
		return list;
	}

}
