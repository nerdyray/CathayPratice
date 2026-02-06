
export interface Data {
  idNum: string;
  chineseName: string;
  gender: string;
  education: string;
  zipCode_1: string;
  address1: string;
  telephone1: string;
  zipCode_2: string;
  address2: string;
  telephone2: string;
  mobile: string;
  email?: string;
  year?: number;
}

export interface T001Tranrq {
  data: Data
}

