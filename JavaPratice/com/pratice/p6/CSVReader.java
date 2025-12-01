package com.pratice.p6;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class CSVReader  {  
    public CSVReader() throws FileNotFoundException {      
    File getCSVFile = new File("D:\\CathayPratice\\JavaPratice\\cars1.csv");
    Scanner sc = new Scanner(getCSVFile);
    String headerLine = sc.nextLine();
    String[] headers = headerLine.split(",");
    List<Map<String,Object>> cars = new ArrayList<>();

    while(sc.hasNext()){
             String line = sc.nextLine();
             String [] values = line.split(",");
             Map<String,Object> map = new HashMap<>();   
             for( int i = 0 ; i < values.length; i++){
                 map.put(headers[i],values[i]);
                }
            cars.add(map);
        }
    }

}
