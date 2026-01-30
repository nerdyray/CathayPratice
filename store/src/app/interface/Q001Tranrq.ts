/**
 * 定義「查詢店家列表」API 的分頁資訊格式
 * `interface` 是 TypeScript 的功能，用來定義物件的「形狀」或「結構」。
 */
export interface Q001TranrqPage {
  pageNumber: number; // 欲查詢的頁碼 (通常從 0 開始)
  pageSize: number;   // 每頁顯示的筆數
}


/**
 * 定義「查詢店家列表」API 的請求主體 (Request Body) 格式
 */
export interface Q001Tranrq {
  storeName: string;      // 欲查詢的店家名稱 (可為空字串，表示查詢全部)
  page: Q001TranrqPage; // 分頁資訊
}
