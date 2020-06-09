package com.example.configuration;

import java.util.Properties;

public class ConfigDbConnection {
    private String driverName;
    private String url;
    private String userName;
    private String password;


    public ConfigDbConnection(Properties properties) {
        this(
                properties.getProperty("driverName"),
                properties.getProperty("url"),
                properties.getProperty("userName"),
                properties.getProperty("password")
        );
    }

    public ConfigDbConnection(String driverName, String url, String userName, String password) {
        this.driverName = driverName;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    //            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/demodb?useUnicode=true&serverTimezone=UTC");
//        dataSource.setUsername("demouser");
//        dataSource.setPassword("demouser");
}
