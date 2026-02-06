import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common'; // 建議加入 CommonModule
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { MatSliderModule } from '@angular/material/slider';
import { Data } from '../../../interface/Q002Tranrq';
import { MatDialog } from '@angular/material/dialog';
import { CustomerService } from '../../../services/customerService';
import { ConfirDelete } from '../../confir-delete/confir-delete';
import { MatSnackBar } from '@angular/material/snack-bar';
const MATERIAL_MODULES = [
  MatSidenavModule,
  MatListModule,
  MatIconModule,
  MatFormFieldModule,
  MatInputModule,
  MatSelectModule,
  MatRadioModule,
  MatCheckboxModule,
  MatButtonModule,
  MatSliderModule
];

@Component({
  selector: 'app-cif001',
  imports: [CommonModule, ReactiveFormsModule, FormsModule, MATERIAL_MODULES],
  templateUrl: './cif001.html',
  styleUrl: './cif001.css',
})
export class Cif001 implements OnInit {

  // 1. 查詢表單
  searchForm: FormGroup;
  yearValue: number = 0; // 滑桿用

  // 2. 表格設定
  displayedColumns: string[] = [
    'idNum',
    'chineseName',
    'gender',
    'education',
    'mobile',
    'email',
    'year',
    'actions' // 這個如果沒寫，HTML 裡的 matColumnDef="actions" 就會報錯
  ];
  dataSource = new MatTableDataSource<Data>([]);

  constructor(
    private fb: FormBuilder,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
    private customerService: CustomerService
  ) {
    // 初始化表單
    this.searchForm = this.fb.group({
      idNum: [''],
      chineseName: [''],
      gender: [''],
      education: [''],
      mobile: [''],
      email: [''],
      year: [0]
    });
  }

  ngOnInit(): void {
    // 預設載入一些假資料方便測試 UI
    this.loadDummyData();
  }

  // --- 核心功能：刪除流程 ---

  /**
   * 步驟 1: 開啟刪除確認彈窗
   * @param customer 當前選中的客戶資料
   */
  // openDeleteDialog(customer: Data): void {
  //   const dialogRef = this.dialog.open(ConfirDelete, {
  //     width: '500px',
  //     disableClose: true, // 強制使用者點擊按鈕才能關閉
  //     data: customer      // 將整筆資料傳給 Dialog 顯示姓名
  //   });

  //   dialogRef.afterClosed().subscribe(confirmed => {
  //     // 如果使用者點擊「確認」(回傳 true)
  //     if (confirmed) {
  //       this.executeDelete(customer['idNum']);
  //     }
  //   });
  // }

  /**
   * 步驟 2: 執行 API 刪除並顯示 Toast
   */
  // executeDelete(id: string): void {
  //   // 呼叫 Service
  //   this.customerService.deleteCustomer(id).subscribe({
  //     next: (res) => {
  //       // 成功：顯示綠色 Toast，並重新查詢資料
  //       this.showToast('刪除成功', 'success-snackbar');
  //       this.refreshTable(id); // 從前端表格移除該筆資料 (或重新呼叫查詢 API)
  //     },
  //     error: (err) => {
  //       // 失敗：顯示粉紅色 Toast
  //       console.error(err);
  //       this.showToast('刪除失敗：查無刪除失敗', 'fail-snackbar');
  //     }
  //   });
  // }

  /**
   * 顯示 SnackBar (Toast)
   */
  private showToast(message: string, panelClass: string) {
    this.snackBar.open(message, '✕', {
      duration: 3000,
      horizontalPosition: 'left', // 左下角
      verticalPosition: 'bottom',
      panelClass: [panelClass]    // 套用自定義 CSS
    });
  }

  // --- 輔助功能 ---

  loadDummyData() {
    const data: Data[] = [
      { idNum: 'A123456789', chineseName: 'Lily', gender: 'F', education: '學士', mobile: '0911111111', email: '123@123.com', year: 20 } as Data,
      { idNum: 'B987654321', chineseName: 'Joan', gender: 'M', education: '碩士', mobile: '0922222222', email: '456@456.com', year: 5 } as Data
    ];
    this.dataSource.data = data;
  }

  // refreshTable(deletedId: string) {
  //   // 簡單實作：直接濾掉刪除的那筆，讓畫面不用整頁重整
  //   this.dataSource.data = this.dataSource.data.filter(c => c.idNum !== deletedId);
  // }
}
