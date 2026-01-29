export interface Tranrq {
  storeId: number;
  storeName: string;
  owner: string;
  tel: string;
  fax: string;
  mobile: string;
  address: string;
  evaluation: string;
  remarks: string;
}


export interface MwHeader {
  MSGID: string;
}

export interface T001Tranrq {

  TRANRQ: Tranrq
}
