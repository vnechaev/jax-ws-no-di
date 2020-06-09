package com.example.db.balance;

import com.example.model.User;
import com.example.model.addUser.ResultCode;
import com.example.model.request.RequestUser;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

public class BalanceDAO implements IBalanceDB{
    private final JdbcTemplate jdbcTemplate;
    private String addUserQuery;

    public BalanceDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public BalanceDAO(JdbcTemplate jdbcTemplate, String tableName) {
        this.jdbcTemplate = jdbcTemplate;
        this.addUserQuery = String.format("insert into %s values (?, ?, ?)", tableName);
    }

    public ResultCode addUser(RequestUser request) throws Exception {
        User user = request.user();
        try {
            String sql = "SELECT COUNT(1) FROM demodb.users WHERE login = ?";
            boolean exists = false;
            int count = jdbcTemplate.queryForObject(sql, new Object[] { user.getLogin() }, Integer.class);
            exists = count > 0;
            if (exists){
                return ResultCode.LOGIN_EXIST;
            }
            jdbcTemplate.update("insert into demodb.users values (?, ?, ?)", null, user.getLogin(), user.getPassword());
            return ResultCode.SUCCESS;

        } catch (DataAccessException e) {
            System.out.println("error occured");
            e.printStackTrace();
            System.out.println(e.getMessage());
            return ResultCode.TECHNICAL_ERROR;
        }
    }

    @Override
    public BigDecimal getBalance(User user) {
        return null;
    }
}
