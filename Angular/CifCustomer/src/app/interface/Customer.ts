/**
 * Customer 接口定義了客戶資料的結構。
 * 這些屬性通常用於在前端應用程式中表示一個客戶的完整資訊。
 */
export interface Customer {
  orderId?: number;     // 訂單ID，可選，數字類型
  idNum: string;        // 身份證字號，必填，字符串類型
  chineseName: string;  // 中文姓名，必填，字符串類型
  gender: string;       // 性別 (例如 'M' 代表男，'F' 代表女)，必填，字符串類型
  education: string;    // 學歷，必填，字符串類型
  zipCode_1: string;     // 戶籍地址郵遞區號，必填，字符串類型
  address1: string;     // 戶籍地址，必填，字符串類型
  telephone1: string;   // 戶籍電話，必填，字符串類型
  zipCode2: string;     // 現居地址郵遞區號，必填，字符串類型
  address2: string;     // 現居地址，必填，字符串類型
  telephone2: string;   // 現居電話，必填，字符串類型
  mobile: string;       // 行動電話，必填，字符串類型
  email?: string;       // 電子郵件，可選，字符串類型
  year?: number;        // 現居年限，可選，數字類型
}

