
/**
 * Data 接口定義了客戶新增 (T001Tranrq) 請求中的實際客戶資料結構。
 * 這些屬性對應於前端表單中收集的客戶資訊。
 */
export interface Data {
  idNum: string;        // 身份證字號，字符串類型
  chineseName: string;  // 中文姓名，字符串類型
  gender: string;       // 性別 (例如 'M' 代表男，'F' 代表女)，字符串類型
  education: string;    // 學歷，字符串類型
  zipCode_1: string;    // 現居地址郵遞區號 (注意：此處名稱可能與實際用途不符，需根據表單邏輯確認)
  address1: string;     // 戶籍地址，字符串類型
  telephone1: string;   // 戶籍電話，字符串類型
  zipCode2: string;    // 戶籍地址郵遞區號 (注意：此處名稱可能與實際用途不符，需根據表單邏輯確認)
  address2: string;     // 現居地址，字符串類型
  telephone2: string;   // 現居電話，字符串類型
  mobile: string;       // 行動電話，字符串類型
  email?: string;       // 電子郵件，可選，字符串類型
  year?: number;        // 現居年限，可選，數字類型
}

/**
 * T001Tranrq 接口定義了客戶新增 API 的完整請求主體 (Request Body) 格式。
 * 包含客戶數據。
 */
export interface T001Tranrq {
  data: Data // 客戶資料
}

