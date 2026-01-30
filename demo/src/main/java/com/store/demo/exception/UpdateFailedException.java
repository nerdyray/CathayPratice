package com.store.demo.exception;

/**
 * 自訂例外類別 (Custom Exception)。
 * 用於表示「更新失敗」的特定錯誤情境。
 * 繼承自 Exception，表示這是一個「受檢例外」(Checked Exception)。
 */
public class UpdateFailedException extends Exception {
    // 序列化版本 ID
    private static final long serialVersionUID = 1L;

}
