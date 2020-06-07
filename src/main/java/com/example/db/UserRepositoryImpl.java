package com.example.db;

import com.example.model.GetBalance.Balance;
import com.example.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUser(String login) {
        User person = null;
        try {
            person = jdbcTemplate.queryForObject("select * from demodb.users where login = ?", new Object[]{login}, USER_MAPPER);
        } catch (DataAccessException dataAccessException) {
            LOGGER.debug("Couldn't find entity of type Person with login {}", login);
        }
        return person;
    }

    @Override
    public Balance getBalance(User user) {
        //1 check user name and password
        //    SUCCESS(0),
        //    TECHNICAL_ERROR(2),
        //    USER_NOT_EXISTS(3),
        //    PASSWORD_INCORRECT(4);
        throw new NotImplementedException();
    }

    @Override
    public boolean addUser(User user) {
        int rowNums = jdbcTemplate.update("insert into demodb.users values (?, ?, ?) ", null,user.getLogin(), user.getPassword());
        if (rowNums == 1){
            return true;
        }else return false;
    }
}
