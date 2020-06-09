package com.example;

import com.example.configuration.ConfigDbConnection;
import com.example.configuration.ConfigDbTables;
import com.example.configuration.DataSourceBuilder;
import com.example.configuration.FileProperties;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;

/**
 * Main class.
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/myapp/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     *
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer(Properties properties) {
        ConfigDbTables dbTables = new ConfigDbTables(properties);
        ConfigDbConnection configDbConnection = new ConfigDbConnection(properties);
        DriverManagerDataSource dataSource = new DataSourceBuilder(configDbConnection)
                .buildDataSource();

        // create a resource config that scans for JAX-RS resources and providers
        // in com.example package
//        final ResourceConfig rc = new ResourceConfig();
        final ResourceConfig rc = new ResourceConfig().packages("com.example");
        rc.register(new MyResource(dataSource, dbTables));
//        rc.register(ValidatingReader.class);
//        rc.register(UnmarshallerResolver.class);

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
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
        server.stop();
    }
}

