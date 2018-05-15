package com.techscore.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionDao {

	@Autowired
	private JdbcTemplate jdbc;
	
	public List<Question> selectRandom(){
		return jdbc.query("SELECT * FROM question ORDER BY random() LIMIT 10;", new BeanPropertyRowMapper<>(Question.class));
	}
	
	public List<Question> selectGenre(String genre){
		return jdbc.query("SELECT * FROM question WHERE genre = ? ORDER BY random() LIMIT 10;", new BeanPropertyRowMapper<>(Question.class), genre);
	}
	
	public List<Question> getData(){
		return jdbc.query("SELECT * FROM question ORDER BY id;", new BeanPropertyRowMapper<>(Question.class));
	}
}
