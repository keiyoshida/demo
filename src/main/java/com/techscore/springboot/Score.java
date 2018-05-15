package com.techscore.springboot;

import java.sql.Timestamp;

import lombok.Data;
import lombok.Getter;

@Data
public class Score {

	private int userId;
	private int score;
	private String genre;
	private Timestamp start_datetime;
	private Timestamp end_datetime;
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Timestamp getStart_datetime() {
		return start_datetime;
	}
	public void setStart_datetime(Timestamp start_datetime) {
		this.start_datetime = start_datetime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
