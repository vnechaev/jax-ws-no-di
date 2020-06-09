package com.example.configuration;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Properties;

public class DataSourceBuilder {

    private ConfigDbConnection config;

    public DataSourceBuilder(Properties properties) {
        this(new ConfigDbConnection(properties));
    }

    public DataSourceBuilder(ConfigDbConnection config) {
        this.config = config;
    }

    public DriverManagerDataSource buildDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(config.getDriverName());
        dataSource.setUrl(config.getUrl());
        dataSource.setUsername(config.getUserName());
        dataSource.setPassword(config.getPassword());
        return dataSource;
    }


}
