// 1. 定義 TRANRQ (交易內容) - 注意這裡的屬性名稱要跟後端 JSON 一模一樣 (snake_case)
export interface Tranrq {
  storeName: string;
  owner: string;
  tel: string;
  fax: string;
  mobile: string;
  address: string;
  evaluation: string;
  remarks: string;
  date: string;
}


export interface MwHeader {
  MSGID: string;
}


export interface CreateStoreTranrq {
  MWHEADER: MwHeader;
  TRANRQ: Tranrq;
}
