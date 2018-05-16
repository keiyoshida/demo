package com.techscore.springboot;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class ScoreDao {

    private JdbcTemplate jdbc;

    public ScoreDao(final JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Score> findRecentlyScores(String userName) {
        return jdbc.query(
                "SELECT t1.* FROM score t1, users t2 WHERE t2.name = ? AND t1.userId = t2.id ORDER BY start_datetime DESC LIMIT 10;",
                new BeanPropertyRowMapper<>(Score.class),
                userName
        );
    }

    public int insert(final Score score) {
        return jdbc.update(
                "INSERT INTO score (userId, score, genre, start_datetime) VALUES (?, ?, ?, ?);",
                score.getUserId(),
                score.getScore(),
                score.getGenre(),
                score.getStartDateTime()
        );
    }

    public void insertScoreData(int userId, String genre, int score, Timestamp start_time) {
        jdbc.update("INSERT INTO score (userId, score, genre, start_datetime) VALUES (?, ?, ?, ?);", userId, score, genre, start_time);
    }
}
