export interface MWHEADER {
  MSGID: string;
  RETURNCODE: string;
  RETURNDESC: string;
}

export interface TRANRS {
  idNum: string;
}

export interface Q003Tranrs {
  MwHeader: MWHEADER;
  tranrs: TRANRS;
}
