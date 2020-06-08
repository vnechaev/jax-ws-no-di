package com.example.db;

import com.example.model.User;
import com.example.model.addUser.ResultCode;
import com.example.model.request.RequestUser;
import com.example.model.response.GetBalanceResponse;
import com.example.model.response.IResponse;
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
    public ResultCode addUser(RequestUser request) throws Exception {
        User user = request.user();
        try {
            boolean exists = userExists(user);
            if (exists) {
                return ResultCode.LOGIN_EXIST;
            }
            jdbcTemplate.update("insert into demodb.users values (?, ?, ?)", null, user.getLogin(), user.getPassword());
            jdbcTemplate.update("insert into demodb.balance (login, balance) values (?, 0)", user.getLogin());
            return ResultCode.SUCCESS;

        } catch (DataAccessException e) {
            System.out.println("error occured");
            e.printStackTrace();
            System.out.println(e.getMessage());
            return ResultCode.TECHNICAL_ERROR;
        }
    }

    @Override
    public IResponse getBalance(RequestUser request) throws Exception {
        User user = request.user();
        try {
            boolean exists = userExists(user);
            if (exists) {
                return new GetBalanceResponse(ResultCode.LOGIN_EXIST.getCode());
            }
            String sql = "select balance from demodb.balance where login = ?";
            BigDecimal balance = jdbcTemplate.queryForObject(sql, new Object[]{user.getLogin()}, BigDecimal.class);

            return new GetBalanceResponse(ResultCode.SUCCESS.getCode(), balance);

        } catch (DataAccessException e) {
            System.out.println("error occured");
            e.printStackTrace();
            System.out.println(e.getMessage());
            return new GetBalanceResponse(ResultCode.TECHNICAL_ERROR.getCode());
        }

    }

    private boolean userExists(User user) {
        String sql = "SELECT COUNT(1) FROM demodb.users WHERE login = ?";
        boolean exists = false;
        int count = jdbcTemplate.queryForObject(sql, new Object[]{user.getLogin()}, Integer.class);
        exists = count > 0;
        return exists;
    }
}
