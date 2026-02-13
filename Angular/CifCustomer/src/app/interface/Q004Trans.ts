export interface Q004Res {
  MWHEADER: Mwheader;
  TRANRS: Tranrs;
}

export interface Mwheader {
  MSGID: string;
  RETURNCODE: string;
  RETURNDESC: string;
}

export interface Tranrs {
  education: Education[];
}

export interface Education {
  MsgOption: string;
  MsgOptionMemo: string;
  MsgOptionSerno: string;
}
