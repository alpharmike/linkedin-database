package com.project.linkedindatabase.connection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceConnector {
    @Value("${spring.datasource.url}")
    static private String connectionUrl;

    @Value("${spring.datasource.username}")
    static private String username;

    @Value("${spring.datasource.password}")
    static private String password;

    static private Connection connection;

    static public Connection establishConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/linkedin?useSSL=false&serverTimezone=UTC", "root", "");
            }

            return connection;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
