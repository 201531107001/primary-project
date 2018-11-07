package com.me.exception;

/**
 * 没有找到学生的异常
 * @author gqm
 *
 */
public class StudentNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private int id;
	
	public StudentNotFoundException(int id) { 
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
