package com.me.entity;

public class Student {
	int id;
	private String name;
	private Integer age;
	private String birth;

	public String getName() { 
		return name;
	}

	public Student() {

	}

	public Student(String name, int age, String birth) {
		super();
		this.name = name;
		this.age = age;
		this.birth = birth;
	}

	public Student(Integer id, String name, int age, String birth) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.birth = birth;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getBirth() {
		return birth;
	}

	public void setBirth(String date) {
		this.birth = date;
	}

}
