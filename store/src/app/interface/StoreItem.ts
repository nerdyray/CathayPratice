/**
 * 定義「單筆店家」資料的詳細格式 (通常用於後端回傳的列表中)
 */
export interface StoreItem {
  storeName: string;      // 店家名稱
  owner: string;          // 負責人
  tel: string;            // 電話
  fax: string;            // 傳真
  mobile: string;         // 手機
  address: string;        // 地址
  evaluationName: string; // 評價名稱 (例如：讚、普通)
  remarks: string;        // 備註
  updateUser: string;     // 最後異動人員
  updateTime: string;     // 最後異動時間
}

/**
 * 定義「查詢店家列表」API 的回應主體 (TRANRS) 格式
 */
export interface StoreTranrs {
  items: StoreItem[];   // 當前頁次的店家資料陣列
  pageNumber: number;   // 當前頁碼
  pageSize: number;     // 每頁筆數
  totalCount: number;   // 總資料筆數
}

/**
 * 定義 API 回應中的標頭 (MWHEADER) 資訊
 */
export interface MwHeader {
  MSGID: string;        // 訊息代碼
  RETURNCODE: string;   // 回應碼 (例如：'0000' 代表成功)
  RETURNDESC: string;   // 回應描述
}
/**
 * 定義一個完整的「查詢店家列表」API 回應格式
 */
export interface ApiResponse {
  MWHEADER: MwHeader;     // 訊息標頭
  TRANRS: StoreTranrs;  // 交易回應主體
}
