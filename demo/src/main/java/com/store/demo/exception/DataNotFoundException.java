package com.store.demo.exception;

/**
 * 自訂例外類別 (Custom Exception)。
 * 用於表示「找不到資料」的特定錯誤情境。
 * 繼承自 Exception，表示這是一個「受檢例外」(Checked Exception)，
 * 呼叫拋出此例外的方法時，必須要處理它 (例如使用 try-catch 或在方法上再次宣告 throws)。
 */
public class DataNotFoundException extends Exception {
    // 序列化版本 ID
    private static final long serialVersionUID = 1L;
}