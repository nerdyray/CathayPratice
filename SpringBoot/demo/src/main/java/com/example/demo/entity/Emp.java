package com.example.demo.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "SPRING_EMP")
public class Emp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "EMP_ID")
    private String empId;

    @Column(name = "EMP_NAME")
    private String empName;

    @Column(name = "CREATE_USER")
    private String createUser;

    @Column(name = "CREATE_DATE")
    private String createDate;
}


