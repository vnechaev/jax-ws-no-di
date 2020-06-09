package com.example.db;

import com.example.model.response.getBalance.Balance;
import com.example.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public interface UserRepository {
    // Маппер, превращающий строку из таблицы БД в объект класса Person
    RowMapper<User> USER_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new User( resultSet.getString("login"), resultSet.getString("password"));
    };
    RowMapper<Balance> BALANCE_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new Balance( resultSet.getBigDecimal("balance"));
    };

    User getUser(String login);

    Balance getBalance(User user);

    boolean addUser(User person);
    //Add wrapers

}

