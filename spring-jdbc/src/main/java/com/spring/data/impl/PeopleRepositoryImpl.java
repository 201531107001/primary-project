package com.spring.data.impl;

import org.springframework.jdbc.core.JdbcOperations;

import com.spring.data.PeopleRepository;

public class PeopleRepositoryImpl implements PeopleRepository{
	
	private JdbcOperations jdbcOperations;
	
	public PeopleRepositoryImpl(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}
	
	@Override
	public void add(String name, double money) {
		String sql = "update people set money = money + ? where name = ?";
		jdbcOperations.update(sql, money, name);
	}

	@Override
	public void sub(String name, double money) {
		String sql = "update people set money = money - ? where name = ?";
		jdbcOperations.update(sql, money, name);
	}

}
