package com.example.db.user;

import com.example.configuration.ConfigDbTables;
import com.example.model.User;
import com.example.model.response.addUser.AddUserResponse;
import com.example.model.response.addUser.ResultCode;
import com.example.model.request.RequestUser;
import com.example.model.response.IResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDAO implements IUserDb {
    private final JdbcTemplate jdbcTemplate;
    private String addUserQuery;
    private ConfigDbTables configDbTables;

    public UserDAO(JdbcTemplate jdbcTemplate, ConfigDbTables configDbTables) {
        this.jdbcTemplate = jdbcTemplate;
        this.configDbTables = configDbTables;
    }

    public UserDAO(JdbcTemplate jdbcTemplate, String tableName) {
        this.jdbcTemplate = jdbcTemplate;
        this.addUserQuery = String.format("insert into %s values (?, ?, ?)", tableName);
    }

    @Override
    public IResponse addUser(RequestUser request) throws Exception {
        User user = request.user();
        try {
            boolean exists = userExists(user);
            if (exists) {
                return new AddUserResponse(ResultCode.LOGIN_EXIST);
            }
            jdbcTemplate.update("insert into demodb.users values (?, ?, ?)", null, user.getLogin(), user.getPassword());
            jdbcTemplate.update("insert into demodb.balance (login, balance) values (?, 0)", user.getLogin());
            return new AddUserResponse(ResultCode.SUCCESS);

        } catch (DataAccessException e) {
            System.out.println("error occured");
            e.printStackTrace();
            System.out.println(e.getMessage());
            return new AddUserResponse(ResultCode.TECHNICAL_ERROR);
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
