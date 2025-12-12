package com.pratice.p3.iwork;

import java.math.BigDecimal;

public abstract class Employee implements IWork{
    protected String name;
    protected String department;
    protected BigDecimal salary;
    public Employee(String name, String department, BigDecimal salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public BigDecimal getSalary() {
        return salary;
    }
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    
}
