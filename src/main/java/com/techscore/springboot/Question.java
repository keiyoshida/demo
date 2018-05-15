package com.techscore.springboot;

import lombok.Data;

@Data
public class Question {

	private int id;
	private String genre;
	private String main_text;
	private String first_text;
	private String second_text;
	private String answer;
	
	public int getId() {
		return id;
	}
	public String getGenre() {
		return genre;
	}
	public String getMain_text() {
		return main_text;
	}
	public String getFirst_text() {
		return first_text;
	}
	public String getSecond_text() {
		return second_text;
	}
	public String getAnswer() {
		return answer;
	}
	public void setId(int id) {
		this.id = id;
		return;
	}
	public void setGenre(String genre) {
		this.genre = genre;
		return;
	}
	public void setMain_text(String main_text) {
		this.main_text = main_text;
		return;
	}
	public void setFirst_text(String first_text) {
		this.first_text = first_text;
		return;
	}
	public void setSecond_text(String second_text) {
		this.second_text = second_text;
		return;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
		return;
	}
}
