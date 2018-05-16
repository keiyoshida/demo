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

    public Sentence(
            int id,
            String mainText,
            String genre,
            String firstText,
            String secondText,
            String answer,
            boolean result,
            String rightAnswer) {
        this.id = id;
        this.main_text = mainText;
        this.genre = genre;
        this.first_text = firstText;
        this.second_text = secondText;
        this.answer = answer;
        this.result = result;
        this.right_answer = rightAnswer;
    }
}
