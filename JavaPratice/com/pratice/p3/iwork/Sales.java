package com.pratice.p3.iwork;

import java.math.BigDecimal;

public class Sales extends Employee{
    private BigDecimal bouns;
    private BigDecimal payment; 
    private BigDecimal performance;
    public  Sales(String name,String department,BigDecimal salary){
        BigDecimal scale = new BigDecimal("0.05");
        BigDecimal payment = salary.multiply(bouns);
        BigDecimal bouns = performance.multiply(scale);
        this.payment=payment;
        this.bouns=bouns;
    }
    @Override
    public void printInfo(){
        System.out.println("");
    }

}
