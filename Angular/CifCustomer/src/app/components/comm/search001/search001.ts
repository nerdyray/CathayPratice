import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule, FormBuilder, FormGroup } from '@angular/forms';

// 導入 Angular Material 相關模組
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { MatSliderModule } from '@angular/material/slider';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavContainer, MatSidenav } from "@angular/material/sidenav";
import { MatNavList } from "@angular/material/list";
import { RouterLink, RouterLinkActive } from '@angular/router';
const MATERIAL_MODULES = [
  MatIconModule,
  MatFormFieldModule,
  MatInputModule,
  MatSelectModule,
  MatRadioModule,
  MatCheckboxModule,
  MatButtonModule,
  MatSliderModule,
];

@Component({
  selector: 'app-search001',

  imports: [RouterLink, CommonModule, ReactiveFormsModule, FormsModule, ...MATERIAL_MODULES, MatSidenavContainer, MatSidenav, MatNavList],
  templateUrl: './search001.html',
  styleUrl: './search001.css', // 注意：新版 Angular CLI 預設可能是 styleUrl (單數) 或 styleUrls (複數)，請依您的專案設定
})
export class Search001 implements OnInit {
  searchForm: FormGroup;

  // 修改：發送的是表單的「值 (Value)」，而不是整個 FormGroup 物件
  @Output() searchEvent = new EventEmitter<any>();

  constructor(private fb: FormBuilder) {
    this.searchForm = this.fb.group({
      idNum: [''],       // 身分證字號
      chineseName: [''], // 姓名
      gender: [''],      // 性別
      education: [''],   // 學歷
      mobile: [''],      // 手機 (若需要)
      email: [''],       // Email (若需要)
      year: [0] // 現居年限
    });
  }

  ngOnInit(): void { }

  /**
   * 按下查詢按鈕
   * 發送表單目前的數值給父元件
   */
  onSearch(): void {
    // 發送 searchForm.value (純資料物件)
    this.searchEvent.emit(this.searchForm.value);
  }

  /**
   * 按下清除按鈕
   * 重置表單為預設值，並自動執行一次「查詢全部」
   */
  onClear(): void {
    // 重置表單，將字串欄位設為空字串，數字欄位設為 0
    this.searchForm.reset({
      idNum: '',
      chineseName: '',
      gender: '',
      education: '',
      mobile: '',
      email: '',
      year: 0
    });

    // 清除後，自動觸發查詢 (等於查詢全部資料)
    this.onSearch();
  }
}
