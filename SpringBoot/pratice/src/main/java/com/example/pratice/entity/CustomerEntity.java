package com.example.pratice.entity;

import java.io.Serial;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SPRING_CUSTOMER")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CUSTOMERId", unique = true, nullable = false)
    private Long customerId;

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "BIRTHDAY", nullable = true)
    private Integer birthday;

    @Column(name = "SEX", length = 1)
    private String sex;

    @Column(name = "ID",unique = true, length = 20)
    private String id;
}
