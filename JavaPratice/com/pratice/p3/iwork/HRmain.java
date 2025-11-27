package com.pratice.p3.iwork;
import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class HRmain {
    public static void main(String[] args) {
        List<Employee>employeeList = new ArrayList<>();
        employeeList.add(new Sales("張志成","信用卡部", BigDecimal.valueOf(35000), BigDecimal.valueOf(6000)));
        employeeList.add(new Sales("林大鈞","保代部",BigDecimal.valueOf(38000),BigDecimal.valueOf(4000)));
        employeeList.add(new Supervisor("李中白","資訊部",BigDecimal.valueOf(65000)));
        employeeList.add(new Supervisor("林小中","理財部",BigDecimal.valueOf(80000)));
        for(Employee employee : employeeList){
            employee.printInfo();
            }
        
            HRmain hrMain = new HRmain();
            hrMain.writeOutput(employeeList);
}
            public void writeOutput(List<Employee>employeeList){
                

                try (BufferedWriter bw = new BufferedWriter(new FileWriter("d:/output.csv"))){
                bw.write("姓名,薪水");
                bw.newLine();  
                for (Employee e : employeeList) {
                bw.write(e.getName() + ","  + e.getSalary());
                bw.newLine();
                    }
                }
                catch (IOException e) {
                System.err.println("Error writing file: " + e.getMessage());
        }
    }
}



