/**
 * Q003Tranrq 接口定義了用於查詢單一客戶的請求數據結構。
 * 主要用於根據身份證字號查詢客戶信息。
 */
export interface Q003Tranrq {
  idNum: string; // 身份證字號，字符串類型 (修正為 string 以符合實際身份證字號格式)
}
