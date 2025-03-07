package com.example.db.user;

import com.example.configuration.ConfigDbTables;
import com.example.db.balance.BalanceDAO;
import com.example.model.User;
import com.example.model.response.addUser.AddUserResponse;
import com.example.model.response.addUser.ResultCode;
import com.example.model.request.RequestUser;
import com.example.model.response.IResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDAO implements IUserDb {
    private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
    private final JdbcTemplate jdbcTemplate;
    private String checkExistanceUser = "select COUNT(1) FROM %s WHERE login = ?";
    private String addUserQuery = "insert into %s (login, password) values (?, ?)";
    private String createBalanceQuery = "insert into %s (login, balance) values (?, 0)";

    public UserDAO(JdbcTemplate jdbcTemplate, ConfigDbTables configDbTables) {
        this.jdbcTemplate = jdbcTemplate;
        this.checkExistanceUser = String.format(checkExistanceUser, configDbTables.getUserDb());
        this.addUserQuery = String.format(addUserQuery, configDbTables.getUserDb());
        this.createBalanceQuery = String.format(createBalanceQuery, configDbTables.getBalanceDb());

    }

    @Override
    public IResponse addUser(RequestUser request) throws Exception {
        User user = request.user();
        try {
            boolean exists = userExists(user);
            if (exists) {
                log.debug("User already exists");
                return new AddUserResponse(ResultCode.LOGIN_EXIST);
            }
            //execute with one jdbc-template invocation?
            jdbcTemplate.update(addUserQuery,  user.getLogin(), user.getPassword());
            jdbcTemplate.update(createBalanceQuery, user.getLogin());
            log.debug("User was successfully added");
            return new AddUserResponse(ResultCode.SUCCESS);

        } catch (Exception e) {
            log.error("Error occured during adding user" , e);
            return new AddUserResponse(ResultCode.TECHNICAL_ERROR);
        }
    }

    private boolean userExists(User user) {
        int count = jdbcTemplate.queryForObject(checkExistanceUser, new Object[]{user.getLogin()}, Integer.class);
        return count > 0;
    }
}
