package com.pratice.p3.iwork;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Sales extends Employee{
    private BigDecimal bonus;
    private BigDecimal payment; 
    private BigDecimal performance;
    public  Sales(String name,String department,BigDecimal salary,BigDecimal performance){
        super(name,department,salary);
        BigDecimal scale = new BigDecimal("0.05");
        this.bonus=performance.multiply(scale);
        this.payment=salary.add(bonus);
        this.performance = performance;
    }
    @Override
    public void printInfo(){
        BigDecimal salaryRound=salary.setScale(0,RoundingMode.HALF_UP);
        BigDecimal paymentRound=payment.setScale(0,RoundingMode.HALF_UP);
        BigDecimal bonusRound=bonus.setScale(0,RoundingMode.HALF_UP);
        System.out.println("薪資單");
        System.out.print("姓名:"+name+" 工作部門:"+department);
        System.out.println();
        System.out.println("月薪:"+salaryRound);
        System.out.println("業績:"+bonusRound);
        System.out.println("薪水: "+paymentRound);
            System.out.println();

    }

}
