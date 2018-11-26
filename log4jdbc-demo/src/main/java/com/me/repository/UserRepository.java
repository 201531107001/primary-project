package com.me.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.me.entity.User;

@Repository
public class UserRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public User getByName(String name) {
		String sql = "select * from tb_user where name = ?";
		return jdbcTemplate.queryForObject(sql, this::userRowMapper, name);
	}
	
	public void save(User user) {
		String sql = "insert into tb_user values(?,?)";
		jdbcTemplate.update(sql, user.getUsername(),user.getAge());
	}
	
	public User userRowMapper(ResultSet rSet ,int rowNum) throws SQLException {
		return new User(rSet.getString(1), rSet.getInt(2));
	}
}
