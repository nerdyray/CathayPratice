package com.pratice.p7.Util;

import com.pratice.p7.Cars;
import java.sql.*;

public class DBUtil {

    private static String username;
    private static String password;
    private static String url;

    static {
        loadConfig();
    }

    private static void loadConfig() {
        try {
            url = "jdbc:mysql://localhost:3306/mysqlpratice";
            username = "ray";
            password = "root"; // 請修改為您的密碼
            System.out.println("✅資料庫設定載入成功");
            System.out.println("連線 URL：" + url);
        } catch (Exception e) {
            System.err.println(" 無法載入資料庫設定：" + e.getMessage());
        }
    }

    //取得連線
    public static Connection getConnection() throws SQLException {
        if (url == null || username == null || password == null) {
            throw new SQLException("資料庫設定未正確");
        }
        return DriverManager.getConnection(url, username, password);
    }

    //測試連線
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            System.err.println("連線測試失敗" + e.getMessage());
            return false;
        }
    }

    //顯示連線資訊
    public static void showConnectionInfo() {
        System.out.println("===資料庫連線資訊===");
        System.out.println("URL" + (url != null ? url : "未設定"));
        System.out.println("使用者名稱：" + (username != null ? username : "未設定"));
        System.out.println("密碼：" + (password != null ? "已設定" : "未設定"));
    }

    // public static void main(String[] args) {
    //     Cars cars = new Cars();
    //     cars.loadDb();
    // }
    //  public static void DBTest() {
    // //     loadConfig();
    // //     try {
    // //         getConnection();
    // //     } catch (Exception e) {
    // //         e.printStackTrace();
    // //     }
    // //     testConnection();
    // //     showConnectionInfo();
    //  }
}
