package com.techscore.springboot;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class UserData {

	private int score;
	private String genre;
	private Timestamp start_datetime;
	
	public UserData(int score, String genre, Timestamp start_datetime) {
		this.setScore(score);
		this.setGenre(genre);
		this.setStart_datetime(start_datetime);
	}

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
}
