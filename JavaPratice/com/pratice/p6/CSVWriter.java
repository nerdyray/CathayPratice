package com.pratice.p6;

import java.io.BufferedWriter;
import java.util.List;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

    public void writeOutput(List<Map<String, Object>> carsList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("d:/cars2.csv"))) {
            bw.write("Manufacturer,TYPE,Min.PRICE,Price");
            bw.newLine();
            for (Map m : carsList) {
                System.out.println(m);
                bw.write(m.get("manufacturer") + "," + m.get("TYPE") + "," + m.get("Min.PRICE") + "," + m.get("Price"));
                bw.newLine();

            }
        } catch (IOException e) {
            System.err.println("Erro writing file" + e.getMessage());
        }
    }
}
