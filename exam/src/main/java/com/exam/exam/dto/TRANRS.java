package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;

/**
 * 一個空的標記類別，用於表示某些交易回應 (Transaction Response) 的本體。
 * 當一個交易的回應不需要任何具體資料時，可以使用這個類別。
 */
public class TRANRS implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

}
