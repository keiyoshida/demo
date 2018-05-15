package com.techscore.springboot;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ScoreDao {

	@Autowired
	private JdbcTemplate jdbc;
	
	public List<Score> selectRecentlyData(String userName) {
		return jdbc.query("SELECT t1.* FROM score t1, users t2 WHERE t2.name = ? AND t1.userId = t2.id ORDER BY start_datetime DESC LIMIT 10;", new BeanPropertyRowMapper<>(Score.class), userName);
	}
	
	public void insertScoreData(int userId, String genre, int score, Timestamp start_time) {
		jdbc.update("INSERT INTO score (userId, score, genre, start_datetime) VALUES (?, ?, ?, ?);", userId, score, genre, start_time);
		return;
	}
}
