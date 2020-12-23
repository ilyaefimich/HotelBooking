package com.epam.jwd.finaltask.dao.impl;

public class ConnectionConfig {
    public static String URL = "jdbc:mysql://localhost/web?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
    public static String USER = "admin";
    public static String PASSWORD = "admin";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
