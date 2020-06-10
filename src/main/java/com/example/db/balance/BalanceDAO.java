package com.example.db.balance;

import com.example.configuration.ConfigDbTables;
import com.example.model.User;
import com.example.model.request.RequestUser;
import com.example.model.response.IResponse;
import com.example.model.response.getBalance.GetBalanceResponse;
import com.example.model.response.getBalance.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

public class BalanceDAO implements IBalanceDB {
    private static final Logger log = LoggerFactory.getLogger(BalanceDAO.class);
    private final JdbcTemplate jdbcTemplate;
    private String getBalance = "select balance from %s where login = ?";
    private String getPassword = "select password FROM %s WHERE login = ?";
    private String checkExistanceUser = "select COUNT(1) FROM %s WHERE login = ?";

    public BalanceDAO(JdbcTemplate jdbcTemplate, ConfigDbTables configDbTables) {
        this.jdbcTemplate = jdbcTemplate;
        this.getBalance = String.format(getBalance, configDbTables.getBalanceDb());
        this.getPassword = String.format(getPassword, configDbTables.getUserDb());
        this.checkExistanceUser = String.format(checkExistanceUser, configDbTables.getUserDb());

    }

    @Override
    public IResponse getBalance(RequestUser request) throws Exception {
        User user = request.user();
        try {
            if (userNotExists(user)) {
                log.debug("User not exist");
                return new GetBalanceResponse(ResultCode.USER_NOT_EXISTS);
            }
            if (passwordWrong(user)) {
                log.debug("Password incorrect");
                return new GetBalanceResponse(ResultCode.PASSWORD_INCORRECT);
            }

            BigDecimal balance = jdbcTemplate.queryForObject(getBalance, new Object[]{user.getLogin()}, BigDecimal.class);
            log.debug("balance retrieved");
            return new GetBalanceResponse(ResultCode.SUCCESS, balance);

        } catch (Exception e) {
            log.error("Error while retrieving balance", e);
            return new GetBalanceResponse(ResultCode.TECHNICAL_ERROR);
        }

    }

    private boolean passwordWrong(User user) {
        String passwordFromDB = jdbcTemplate.queryForObject(getPassword, new Object[]{user.getLogin()}, String.class);
        return !passwordFromDB.equals(user.getPassword());
    }

    private boolean userNotExists(User user) {
        int count = jdbcTemplate.queryForObject(checkExistanceUser, new Object[]{user.getLogin()}, Integer.class);
        return count < 0;
    }
}
