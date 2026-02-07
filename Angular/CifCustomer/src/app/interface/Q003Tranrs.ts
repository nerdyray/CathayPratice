/**
 * MWHEADER 接口定義了後端響應消息頭的標準結構。
 * 用於包含消息ID、返回代碼和返回描述。
 */
export interface MWHEADER {
  MSGID: string;       // 消息ID，字符串類型
  RETURNCODE: string;  // 返回代碼，字符串類型 (例如 '0000' 表示成功)
  RETURNDESC: string;  // 返回描述，字符串類型 (對返回代碼的文字說明)
}

/**
 * TRANRS 接口定義了身份證字號查詢響應中的交易結果數據結構。
 * 通常包含查詢到的身份證字號信息。
 */
export interface TRANRS {
  idNum: string; // 身份證字號，字符串類型
}

/**
 * Q003Tranrs 接口定義了身份證字號查詢 API 的完整響應主體 (Response Body) 格式。
 * 包含消息頭和交易結果。
 */
export interface Q003Tranrs {
  MwHeader: MWHEADER; // 消息頭
  tranrs: TRANRS;     // 交易結果數據
}
