/**
 * 定義「店家」物件的資料模型 (Data Model)。
 * 這個 interface 主要在前端應用程式內部使用，
 * 用來確保所有跟「店家」相關的物件都有統一的屬性和型別。
 */
export interface Store {
  storeId: number;      // 店家唯一識別碼
  storeName: string;    // 店家名稱
  owner: string;        // 負責人
  tel: string;          // 電話
  fax: string;          // 傳真
  mobile: string;       // 手機
  address: string;      // 地址
  evaluation: string;   // 評價 (代碼)
  remarks: string;      // 備註
  updateTime: string;   // 最後更新時間
  updateUser: string;   // 最後更新人員
}
