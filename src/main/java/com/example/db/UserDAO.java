package com.example.db;

import com.example.model.User;
import com.example.model.request.RequestUser;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

public class UserDAO implements IUserDb {
    private final JdbcTemplate jdbcTemplate;
    private String addUserQuery;

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserDAO(JdbcTemplate jdbcTemplate, String tableName) {
        this.jdbcTemplate = jdbcTemplate;
        this.addUserQuery = String.format("insert into %s values (?, ?, ?)", tableName);
    }

    @Override
    public boolean addUser(RequestUser request) throws Exception {
        User user = request.user();
        try {
            int rowNums = jdbcTemplate.update("insert into demodb.users values (?, ?, ?)", null, user.getLogin(), user.getPassword());
            if (rowNums == 1) {
                System.out.println( String.format("User with login %s was succcessfully added", user.getLogin()));
                return true;
            } else {
                System.out.println("error while adding");
                return false;
            }
        } catch (DataAccessException e) {
            System.out.println("error occured");
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public BigDecimal getBalance(User user) {
        return null;
    }
}
