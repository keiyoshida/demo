package com.techscore.springboot;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    private final JdbcTemplate jdbc;

    public UserDao(final JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public User findUserByName(String userName) {
        return jdbc.queryForObject("SELECT * FROM Users WHERE name = ?", new BeanPropertyRowMapper<>(User.class), userName);
    }

//    public List<User> checkLogin(String userName, String password) {
//        return jdbc.query("SELECT * FROM Users WHERE name = ? AND password = ?", new BeanPropertyRowMapper<>(User.class), userName, password);
//    }

    public User findUserById(int userId) {
        return jdbc.queryForObject("SELECT * FROM Users WHERE id = ?", new BeanPropertyRowMapper<>(User.class), userId);
    }

    public int insert(final User user) {
        return jdbc.update("INSERT INTO users (name, password) VALUES (?, ?)", user.getName(), user.getPassword());
    }

    public void insertUser(String userName, String password) {
        jdbc.update("INSERT INTO Users (name, password) VALUES (? ,?)", userName, password);
    }

}
