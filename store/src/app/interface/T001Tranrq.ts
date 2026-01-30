/**
 * 定義「更新店家」API 的請求主體 (Request Body) 格式
 */
export interface EditTranrq {
  storeId: number;    // 欲更新的店家 ID
  storeName: string;  // 店家名稱
  owner: string;      // 負責人
  tel: string;        // 電話
  fax: string;        // 傳真
  mobile: string;     // 手機
  address: string;    // 地址
  evaluation: string; // 評價 (代碼)
  remarks: string;    // 備註
}


/**
 * 定義 API 請求中的標頭 (MWHEADER) 資訊
 */
export interface MwHeader {
  MSGID: string; // 訊息代碼，用來識別是哪個交易
}

/**
 * 定義一個完整的「更新店家」API 請求格式 (T001)
 */
export interface T001Tranrq {
  TRANRQ: EditTranrq; // 交易請求主體
}
