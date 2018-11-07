package com.me.entity;

public class Student {
	int id;
	private String name;
	private int age;
	private String date;

	public String getName() { 
		return name;
	}

	public Student() {

	}

	public Student(String name, int age, String date) {
		super();
		this.name = name;
		this.age = age;
		this.date = date;
	}

	public Student(int id, String name, int age, String date) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
