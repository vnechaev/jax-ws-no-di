package com.example.configuration;

import java.io.*;
import java.util.Properties;

public class FileProperties {
    private InputStream inputStream;

    public FileProperties(String fileName) throws FileNotFoundException {
        this(new File(fileName));
    }

    public FileProperties(File file) throws FileNotFoundException {
        this(new FileInputStream(file));
    }

    public FileProperties(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Properties loadConfig() throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }
}
