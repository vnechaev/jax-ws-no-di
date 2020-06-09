package com.example.configuration;

public class ConfigDbTables {
    private String userDb;
    private String balanceDb;

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
