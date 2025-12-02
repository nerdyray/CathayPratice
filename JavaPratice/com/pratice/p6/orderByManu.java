package com.pratice.p6;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class orderByManu {

    public static void arrMan(List<Map<String, Object>> carsList) {
        String prevManu = null;
        BigDecimal sumPrice = BigDecimal.ZERO;
        BigDecimal sumMin = BigDecimal.ZERO;
        Collections.sort(carsList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> a, Map<String, Object> b) {
                String manuA = a.get("manufacturer").toString();
                String manuB = b.get("manufacturer").toString();
                int result = manuA.compareTo(manuB);
                if (result != 0) {
                    return result;
                }
                Object priceAObj = a.get("Price");
                Object priceBObj = b.get("Price");
                BigDecimal priceA = new BigDecimal(priceAObj.toString());
                BigDecimal priceB = new BigDecimal(priceBObj.toString());
                return priceA.compareTo(priceB);
            }
        }
        );
        System.out.printf("%-15s %-10s %-12s %-10s\n",
                "Manufacturer", "TYPE", "Min.PRICE", "Price");
        BigDecimal totalMin = BigDecimal.ZERO;
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Map m : carsList) {
            String currentManu = m.get("manufacturer").toString();
            BigDecimal price = new BigDecimal(m.get("Price").toString());
            BigDecimal min = new BigDecimal(m.get("Min.PRICE").toString());
            if (prevManu != null && !currentManu.equals(prevManu)) {
                System.out.printf("小計 %-21s %s %12s\n", "", sumPrice, sumMin);
                System.out.println(); // 空行
                sumPrice = BigDecimal.ZERO;
                sumMin = BigDecimal.ZERO;
            }
            sumMin = sumMin.add(min);
            sumPrice = sumPrice.add(price);

            System.out.printf("%-15s %-10s %-12s %-10s\n",
                    currentManu,
                    m.get("TYPE"),
                    min,
                    price);
            prevManu = currentManu;
            totalPrice = totalPrice.add(price);
            totalMin = totalMin.add(min);
        }
        System.out.printf("小計 %-21s %s %12s\n", "", sumPrice, sumMin);
        System.out.printf("總計 %-21s %s %12s\n", "", totalPrice, totalMin);

    }

}
