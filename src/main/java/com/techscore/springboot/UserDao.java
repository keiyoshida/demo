package com.techscore.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	public List<User> check_registration(String userName) {
		return jdbc.query("SELECT * FROM Users WHERE name = ?", new BeanPropertyRowMapper<>(User.class), userName);
	}
	
	public List<User> check_login(String userName, String password) {
		return jdbc.query("SELECT * FROM Users WHERE name = ? AND password = ?", new BeanPropertyRowMapper<>(User.class), userName, password);
	}
	
	public List<User> select_userId(int userId) {
		return jdbc.query("SELECT * FROM Users WHERE id = ?", new BeanPropertyRowMapper<>(User.class), userId);
	}
	
	public void insertUser(String userName, String password) {
		jdbc.update("INSERT INTO Users (name, password) VALUES (? ,?)", userName, password);
	}

}
