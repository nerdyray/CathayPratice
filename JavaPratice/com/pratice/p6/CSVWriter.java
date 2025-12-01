package com.pratice.p6;
import java.io.BufferedWriter;
import java.util.List;
import java.io.FileWriter;

public class CSVWriter {
    public void writeOutput(List<Cars>carsList){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("d:/cars2.csv"))){
            bw.write("Manufacturer,TYPE,Min.PRICE,Price");
            bw.newLine();
            for(Cars c: c){
                bw.write(c.get)
            }
        }
    }
}
