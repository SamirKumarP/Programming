package com.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentMap {

	public static void main(String[] args) {
		List<Student> students = new ArrayList<Student>(Arrays.asList(
				new Student("Sam" , "Physics"),
				new Student("Sam" , "Chemistry"),
				new Student("Sam" , "Maths"),
				new Student("Sam" , "Bio"),
				new Student("Samir" , "Physics"),
				new Student("Samir" , "Chemistry"),
				new Student("Samir" , "Maths"),
				new Student("Jitu" , "Physics"),
				new Student("Jitu" , "Chemistry"),
				new Student("Jitu" , "Bio")
				));
		
		Map<String, List<String>> outputMapByAttributes = new HashMap<>();
		outputMapByAttributes  = students.stream().collect(Collectors.groupingBy(x -> x.getStudentName(), Collectors.mapping(x -> x.getCourseId(), Collectors.toList())));
		Map<String, List<Student>> outputMapByObject = new HashMap<>();
		outputMapByObject  = students.stream().collect(Collectors.groupingBy(Student::getStudentName));
		System.out.println(outputMapByAttributes);
		System.out.println(outputMapByObject);

	}

}
