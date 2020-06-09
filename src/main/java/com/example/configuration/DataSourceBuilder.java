package com.example.configuration;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceBuilder {

    private ConfigDbConnection config;

    public DataSourceBuilder(ConfigDbConnection config) {
        this.config = config;
    }

    public DriverManagerDataSource buildDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(config.getDriverName());
        dataSource.setUrl(config.getUrl());
        dataSource.setUsername(config.getUserName());
        dataSource.setPassword(config.getPassword());
        return dataSource;
    }


}
