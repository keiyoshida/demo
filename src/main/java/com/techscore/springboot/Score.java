package com.techscore.springboot;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Score {

    private int userId;

    private int score;

    private String genre;

    private Timestamp startDateTime;

    private Timestamp endDateTime;
}
