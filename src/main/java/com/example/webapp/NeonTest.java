package com.example.webapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NeonTest {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://ep-shy-cake-b4juylt8-pooler.c-6.us-east-2.aws.neon.tech/neondb?user=neondb_owner&password=npg_RUaLeWr1fF2X&sslmode=require";

        try (Connection conn = DriverManager.getConnection(url)) {
            System.out.println("Neon(Postgres)への接続に成功しました！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
