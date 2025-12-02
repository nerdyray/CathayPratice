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
            orderByManu.arrMan(carsList);
            CSVWriter csvWriter = new CSVWriter();
            csvWriter.writeOutput(carsList);
            // csvWriter.arrByMan(carsList);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}
