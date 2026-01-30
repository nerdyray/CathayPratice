package com.store.demo.dto;

import java.io.Serial;
import java.io.Serializable;

/**
 * 這是一個空的 DTO (Data Transfer Object) 物件。
 * 在這個專案中，它被用作一個標記，
 * 代表某些交易 (例如新增、更新、刪除) 成功後，
 * 回應的資料本體 (Response Body) 是空的。
 * implements Serializable: 表示這個物件可以被序列化。
 */
public class TRANRS implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

}