package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
@Data
public class Q004Tranrs implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    private List<Q004TranrsEducation> education;
}
