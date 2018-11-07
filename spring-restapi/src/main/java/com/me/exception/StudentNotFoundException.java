package com.me.exception;

/**
 * û���ҵ�ѧ�����쳣
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
