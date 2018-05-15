package com.techscore.springboot;

import lombok.Data;

@Data
public class Sentence {

	private int id;
	private String genre;
	private String main_text;
	private String first_text;
	private String second_text;
	private String answer;
	private boolean result;
	private String right_answer;
	
	public Sentence(int id, String main_text, String genre, String first_text, String second_text, String answer, boolean result, String right_answer) {
		this.id = id;
		this.main_text = main_text;
		this.genre = genre;
		this.first_text = first_text;
		this.second_text = second_text;
		this.answer = answer;
		this.result = result;
		this.right_answer = right_answer;
	}

	public int getId() {
		return id;
	}

	public String getFirst_text() {
		return first_text;
	}

	public String getSecond_text() {
		return second_text;
	}

	public String getMain_text() {
		return main_text;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public boolean getResult() {
		return result;
	}
	
	public String getRight_answer() {
		return right_answer;
	}
}
