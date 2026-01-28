export interface StoreItem {
  storeName: string;
  owner: string;
  tel: string;
  fax: string;
  mobile: string;
  address: string;
  evaluation: string;
  remarks: string;
  updateUser: string,
  updateTime: string;
}

export interface StoreTranrs {
  items: StoreItem[];
  pageNumber: number;
  pageSize: number;
  totalCount: number;
}

export interface MwHeader {
  MSGID: string;
  RETURNCODE: string;
  RETURNDESC: string;
}
export interface ApiResponse {
  MWHEADER: MwHeader;
  TRANRS: StoreTranrs;
}
