/**
 * 定義「刪除店家」API 的請求主體 (Request Body) 中的 TRANRQ 部分
 */
export interface Tranrq {
  storeId: number; // 欲刪除的店家 ID
}


/**
 * 定義 API 請求中的標頭 (MWHEADER) 資訊
 */
export interface MwHeader {
  MSGID: string; // 訊息代碼，用來識別是哪個交易
}

/**
 * 定義一個完整的「刪除店家」API 請求格式 (T003)
 */
export interface T003Tranrq {

  TRANRQ: Tranrq; // 交易請求主體
}
