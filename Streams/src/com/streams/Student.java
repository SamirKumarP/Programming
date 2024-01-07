package com.streams;

public class Student {
	
	public Student(String studentName, String courseId) {
		this.studentName = studentName;
		this.courseId = courseId;
	}
	public String studentName;
	public String courseId;
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

}
