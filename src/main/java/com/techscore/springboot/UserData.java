package com.techscore.springboot;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserData {

    private int score;
    private String genre;
    private Timestamp startDatetime;

    public UserData(int score, String genre, Timestamp start_datetime) {
        this.setScore(score);
        this.setGenre(genre);
        this.setStartDatetime(start_datetime);
    }
}
