package com.example.db;

import com.example.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

public class UserDAO implements IUserDb{
    private final JdbcTemplate jdbcTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean addUser(User user) {
        try {
            int rowNums = jdbcTemplate.update("insert into demodb.users values (?, ?, ?) ", null,user.getLogin(), user.getPassword());
            if (rowNums == 1){
                return true;
            }else return false;
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
