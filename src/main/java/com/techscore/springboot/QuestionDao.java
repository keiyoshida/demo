package com.techscore.springboot;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDao {

    private JdbcTemplate jdbc;

    public QuestionDao(final JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Question> findQuestions() {
        return jdbc.query(
                "SELECT * FROM question ORDER BY id;",
                new BeanPropertyRowMapper<>(Question.class)
        );
    }

    public List<Question> findQuestionsAtRandom() {
        return jdbc.query(
                "SELECT * FROM question ORDER BY random() LIMIT 10;",
                new BeanPropertyRowMapper<>(Question.class)
        );
    }

    public List<Question> findQuestionsByGenreAtRandom(String genre) {
        return jdbc.query(
                "SELECT * FROM question WHERE genre = ? ORDER BY random() LIMIT 10;",
                new BeanPropertyRowMapper<>(Question.class),
                genre
        );
    }
}
