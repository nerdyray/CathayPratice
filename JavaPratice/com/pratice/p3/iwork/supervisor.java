package com.pratice.p3.iwork;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Supervisor extends Employee {

    private BigDecimal payment;

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Supervisor(String name, String department, BigDecimal salary) {
        super(name, department, salary);
        this.payment = salary;

    }

    @Override
    public void printInfo() {
        BigDecimal paymentRound = payment.setScale(0, RoundingMode.HALF_UP);
        System.out.println("薪資單");
        System.out.println("姓名:" + name + " 工作部門:" + department);
        System.out.println("薪水: " + paymentRound);
        System.out.println();

    }

}
