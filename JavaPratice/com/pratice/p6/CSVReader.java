package com.pratice.p6;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CSVReader {

    //建立Read方法，讓carsList可以從外部存取
    public static List<Map<String, Object>> read() throws FileNotFoundException {
        File getCSVFile = new File("d:/CathayPratice/JavaPratice/cars1.csv");
        try (Scanner sc = new Scanner(getCSVFile)) {
            String headerLine = sc.nextLine();
            String[] headers = headerLine.split(",");
            List<Map<String, Object>> cars = new ArrayList<>();

            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] values = line.split(",");
                Map<String, Object> map = new HashMap<>();
                for (int i = 0; i < values.length; i++) {
                    map.put(headers[i], values[i]);
                }
                cars.add(map);
            }
            Collections.sort(cars, (a, b) -> {
                BigDecimal priceA = new BigDecimal(a.get("Price").toString());
                BigDecimal priceB = new BigDecimal(b.get("Price").toString());
                return priceA.compareTo(priceB);

            });
            sc.close();
            return cars;
        }
    }
}

/*
        Collections.sort(cars, new Comparator<Map<String, Object>>() {
            @Override
        public int compare(Map<String, Object> a, Map<String, Object> b) {
        Object priceAObj = a.get("Price");
        Object priceBObj = b.get("Price");
        BigDecimal priceA = new BigDecimal(priceAObj.toString());
        BigDecimal priceB = new BigDecimal(priceBObj.toString());
        return priceA.compareTo(priceB);
        }
        });
        改寫成lambda語法
 */
