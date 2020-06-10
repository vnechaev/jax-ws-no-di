package com.example;

import com.example.configuration.ConfigDbTables;
import com.example.configuration.DataSourceBuilder;
import com.example.configuration.FileProperties;
import com.example.exception.LogAllExceptions;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;


public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/myapp/";

    public static HttpServer startServer(Properties properties) {
        ConfigDbTables dbTables = new ConfigDbTables(properties);
        DriverManagerDataSource dataSource = new DataSourceBuilder(properties)
                .buildDataSource();
        final ResourceConfig rc = new ResourceConfig();
        rc.register(new MyResource(dataSource, dbTables));
        rc.register(LogAllExceptions.class);

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
        return server;
    }

    public static void main(String[] args) throws IOException {
        String propsFileName = "config.properties";
        if (args.length > 0) {
            propsFileName = args[0];
        }
        Properties props = new FileProperties(propsFileName).loadConfig();

        final HttpServer server = startServer(props);
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.shutdown();
    }
    //TODO add shutdown hook
    //TODO assembly executable jar
}

