package com.me.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
	private String uid;
	private String username;
	private String password;
	private String wage;
	private int workAge;
	private int age;
	private int sex;
}
