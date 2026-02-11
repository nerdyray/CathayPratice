
/**
 * Data 接口定義了客戶查詢的數據結構。
 * 這些屬性通常用於在客戶列表查詢時作為篩選條件。
 */
export interface Data {
  orderId?: number;     // 訂單ID，可選，數字類型
  idNum: string;        // 身份證字號，字符串類型
  chineseName: string;  // 中文姓名，字符串類型
  gender: string;       // 性別，字符串類型
  education: string;    // 學歷，字符串類型
  zipCode_1: string;     // 戶籍地址郵遞區號，字符串類型
  address1: string;     // 戶籍地址，字符串類型
  telephone1: string;   // 戶籍電話，字符串類型
  zipCode2: string;     // 現居地址郵遞區號，字符串類型
  address2: string;     // 現居地址，字符串類型
  telephone2: string;   // 現居電話，字符串類型
  mobile: string;       // 行動電話，字符串類型
  email?: string;       // 電子郵件，可選，字符串類型
  year?: number;        // 現居年限，可選，數字類型
}

/**
 * StoreInfo 接口定義了排序資訊的結構。
 * 用於指定查詢結果的排序方式和排序字段。
 */
export interface StoreInfo {
  sortBy: string,     // 排序方向 (例如 "ASC" 或 "DESC")
  sortColumn: string  // 排序的字段名稱
}

/**
 * 定義「查詢店家列表」API 的分頁資訊格式。
 * `interface` 是 TypeScript 的功能，用來定義物件的「形狀」或「結構」。
 */
export interface Q002TranrqPage {
  pageNumber: number; // 欲查詢的頁碼 (通常從 0 開始)
  pageSize: number;   // 每頁顯示的筆數
}


/**
 * 定義「查詢客戶列表」API 的請求主體 (Request Body) 格式。
 * 包含查詢條件、分頁資訊和排序資訊。
 */
export interface Q002Tranrq {
  data: Data;           // 欲查詢的客戶資料 (作為篩選條件，可包含空字串表示不篩選該字段)
  page: Q002TranrqPage; // 分頁資訊
  storeInfo: StoreInfo; // 排序資訊
}


