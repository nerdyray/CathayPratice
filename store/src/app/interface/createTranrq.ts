/**
 * 定義「新增店家」API 的請求主體 (Request Body) 中的 TRANRQ 部分
 * 這裡的屬性名稱需要和後端 API 定義的 JSON 格式完全一致。
 */
export interface Tranrq {
  storeName: string;  // 店家名稱
  owner: string;      // 負責人
  tel: string;        // 電話
  fax: string;        // 傳真
  mobile: string;     // 手機
  address: string;    // 地址
  evaluation: string; // 評價 (代碼)
  remarks: string;    // 備註
  date: string;       // 異動日期
}


/**
 * 定義 API 請求中的標頭 (MWHEADER) 資訊
 */
export interface MwHeader {
  MSGID: string; // 訊息代碼，用來識別是哪個交易
}


/**
 * 定義一個完整的「新增店家」API 請求格式
 */
export interface CreateStoreTranrq {
  MWHEADER: MwHeader; // 訊息標頭
  TRANRQ: Tranrq;     // 交易請求主體
}
