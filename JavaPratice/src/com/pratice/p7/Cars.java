package com.pratice.p7;

import java.util.Scanner;
import com.pratice.p7.Util.DBUtil;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cars {

    Scanner sc = new Scanner(System.in);

    public void loadDb() {
        String sql = "Select * from cars order by Manufacturer";
        List<Map<String, Object>> cars = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("Manufacturer", rs.getString("Manufacturer"));
                map.put("Type", rs.getString("Type"));
                map.put("Min_Price", rs.getBigDecimal("Min_Price"));
                map.put("Price", rs.getBigDecimal("Price"));
                cars.add(map);
            }
            for (Map m : cars) {
                System.out.println(m);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void App() {
        String man;
        String type;
        BigDecimal Min_Price;
        BigDecimal Price;

        while (true) {
            String sql = "Select * from cars order by Manufacturer,Type";
            try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

                System.out.println("請輸入以下指令: Select Insert Update Delete");

                String userInput = sc.nextLine();
                switch (userInput) {
                    case "Select":
                        loadDb();
                        break;
                    case "Insert":
                        Map<String, Object> m = new HashMap<>();
                        System.out.println("請輸入製造商");
                        man = sc.nextLine();
                        m.put("Manufacturer", man);

                        System.out.println("請輸入類型");
                        String Type = sc.nextLine();
                        m.put("Type", Type);

                        System.out.println("請輸入底價");
                        Min_Price = sc.nextBigDecimal();
                        sc.nextLine(); // 清除換行

                        m.put("Min_Price", Min_Price);
                        System.out.println("請輸入售價");
                        Price = sc.nextBigDecimal();
                        sc.nextLine();
                        m.put("Price", Price);

                        String insertSql = "INSERT INTO cars (Manufacturer, Type, Min_Price, Price)Values(?,?,?,?)";
                        PreparedStatement insertPstmt = conn.prepareStatement(insertSql);
                        insertPstmt.setString(1, (String) m.get("Manufacturer"));
                        insertPstmt.setString(2, (String) m.get("Type"));
                        insertPstmt.setBigDecimal(3, (BigDecimal) m.get("Min_Price"));
                        insertPstmt.setBigDecimal(4, (BigDecimal) m.get("Price"));
                        insertPstmt.executeUpdate();

                        break;

                    case "Update":
                        Map<String, Object> updateMap = new HashMap<>();

                        System.out.println("請輸入要更新的製造商");
                        man = sc.nextLine();
                        updateMap.put("Manufacturer", man);

                        System.out.println("請輸入要更新的類型");
                        Type = sc.nextLine();
                        updateMap.put("Type", Type);

                        System.out.println("請輸入新底價");
                        Min_Price = sc.nextBigDecimal();
                        sc.nextLine(); // 清除換行 
                        updateMap.put("Min_Price", Min_Price);

                        System.out.println("請輸入新售價");
                        Price = sc.nextBigDecimal();
                        sc.nextLine(); // 清除換行
                        updateMap.put("Price", Price);

                        String updateSql = "update cars SET  Min_Price=?, Price=? "
                                + " WHERE Manufacturer=? and Type =?";
                        PreparedStatement updatePstmt = conn.prepareStatement(updateSql);
                        updatePstmt.setBigDecimal(1, (BigDecimal) updateMap.get("Min_Price"));
                        updatePstmt.setBigDecimal(2, (BigDecimal) updateMap.get("Price"));
                        updatePstmt.setString(3, (String) updateMap.get("Manufacturer"));
                        updatePstmt.setString(4, (String) updateMap.get("Type"));
                        int rows = updatePstmt.executeUpdate();
                        if (rows > 0) {
                            System.out.println("更新成功！");
                        } else {
                            System.out.println("沒有找到符合條件的資料！");
                        }
                        break;
                    case "Delete":
                        Map<String, Object> deleteMap = new HashMap<>();

                        System.out.println("請輸入要刪除的製造商");
                        man = sc.nextLine();
                        deleteMap.put("Manufacturer", man);
                        System.out.println("請輸入要刪除的類型");
                        Type = sc.nextLine();
                        deleteMap.put("Type", Type);
                        String deleteSql = "delete from cars"
                                + " WHERE Manufacturer=? and Type =?";
                        PreparedStatement deletePstmt = conn.prepareStatement(deleteSql);
                        deletePstmt.setString(1, (String) deleteMap.get("Manufacturer"));
                        deletePstmt.setString(2, (String) deleteMap.get("Type"));
                        int rows = deletePstmt.executeUpdate();
                        if (rows > 0) {
                            System.out.println("刪除成功！");
                        } else {
                            System.out.println("沒有找到符合條件的資料！");
                        }
                        break;

                    default:
                        throw new AssertionError();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
