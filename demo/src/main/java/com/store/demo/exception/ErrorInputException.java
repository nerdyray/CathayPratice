package com.store.demo.exception;

/**
 * 自訂例外類別 (Custom Exception)。
 * 用於表示「輸入資料錯誤」的特定錯誤情境，例如必填欄位未填、格式錯誤等。
 * 繼承自 Exception，表示這是一個「受檢例外」(Checked Exception)。
 */
public class ErrorInputException extends Exception {
    // 序列化版本 ID
    private static final long serialVersionUID = 1L;
}