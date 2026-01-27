export interface Q001TranrqPage {
  pageNumber: number;
  pageSize: number;
}


export interface Q001Tranrq {
  storeId?: number;
  storeName: string;
  page: Q001TranrqPage;
}
