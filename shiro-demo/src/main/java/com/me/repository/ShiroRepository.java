package com.me.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShiroRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public String getPasswordByUsername(String username) {
		String sql = "select PASSWORD from SHIRO_USER where USER_NAME = ?";
		String password = jdbcTemplate.queryForObject(sql, String.class, username);
		return password;
	}

	public List<String> getPermissionByUserName(String username) {
		String sql = "select P.PERM_NAME from SHIRO_ROLE_PERMISSION P inner join SHIRO_USER_ROLE R on P.ROLE_NAME=R.ROLE_NAME where R.USER_NAME = ?";
		List<String> list = jdbcTemplate.queryForList(sql, String.class,username);
		return list;
	}
}
