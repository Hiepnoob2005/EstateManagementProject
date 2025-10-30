package com.javaweb.utils;


import org.springframework.context.annotation.PropertySource;

import java.sql.Connection;
import java.sql.DriverManager;

@PropertySource("classpath:application.properties")
public class ConnectionJDBCUtils {
    static final String DB_URL = "jdbc:mysql//localhost:3306/estateadvance";
    static final String USER = "root";
    static final String PASS = "hien2005";
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            return conn;
        }
        catch(Exception ex){
            ex.printStackTrace();

        }
        return conn;
    }
}
