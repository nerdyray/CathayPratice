package com.store.demo.dto;

import java.io.Serial;
import java.io.Serializable;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Q001Tranrs implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @JsonProperty("pageSize")
    private int pageSize;
    @JsonProperty("pageNumber")
    private int pageNumber;
    @JsonProperty("totalPage")
    private int totalPage;
    @JsonProperty("totalCount")
    private Long totalCount;
    @JsonProperty("items")
    private List<Q001TranrsItems> items;
}
