package com.pratice.p6;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Comparator;

public class outPut {

    public static void main(String[] args) {

        try {
            List<Map<String, Object>> carsList = CSVReader.read();
            Collections.sort(carsList, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> a, Map<String, Object> b) {
                    Object priceAObj = a.get("Price");
                    Object priceBObj = b.get("Price");
                    BigDecimal priceA = new BigDecimal(priceAObj.toString());
                    BigDecimal priceB = new BigDecimal(priceBObj.toString());
                    return priceA.compareTo(priceB);
                }
            });
            CSVWriter csvWriter = new CSVWriter();
            csvWriter.writeOutput(carsList);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
