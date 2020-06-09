package com.example.configuration;

import java.util.Properties;

public class ConfigDbTables {
    private String userDb;
    private String balanceDb;

    public ConfigDbTables(Properties properties) {
        this(
                properties.getProperty("db.table.user"),
                properties.getProperty("db.table.balance")
        );
    }

    public ConfigDbTables(String userDb, String balanceDb) {
        this.userDb = userDb;
        this.balanceDb = balanceDb;
    }


    public String getUserDb() {
        return userDb;
    }

    public String getBalanceDb() {
        return balanceDb;
    }
}
