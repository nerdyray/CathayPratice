import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule, FormBuilder, FormGroup } from '@angular/forms';

// Material Modules
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator'; // 1. 確保引入 Paginator
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
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

// 你的 Service & Interface
import { Data } from '../../../interface/Q002Tranrq';
import { CustomerService } from '../../../services/customerService';

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
  MatSliderModule,
  MatTableModule,
  MatPaginatorModule // 2. 確保這裡有加
];

@Component({
  selector: 'app-cif001',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule, ...MATERIAL_MODULES],
  templateUrl: './cif001.html',
  styleUrl: './cif001.css',
})
export class Cif001 implements OnInit {

  // ★★★ 關鍵修正：這裡必須宣告變數，否則會報 does not exist 錯誤 ★★★
  totalItems: number = 0;   // 對應 HTML [length]="totalItems"
  pageSize: number = 5;     // 對應 HTML [pageSize]="pageSize"
  pageIndex: number = 0;    // 對應 HTML [pageIndex]="pageIndex"

  searchForm: FormGroup;

  // 定義表格欄位 (對應 HTML 的 matColumnDef)
  displayedColumns: string[] = [
    'idNum',
    'chineseName',
    'gender',
    'education',
    'mobile',
    'email',
    'year',
    'actions'
  ];

  dataSource = new MatTableDataSource<Data>([]);

  constructor(
    private fb: FormBuilder,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
    private customerService: CustomerService
  ) {
    this.searchForm = this.fb.group({
      idNum: [''],
      chineseName: [''],
      gender: [''],
      education: [''],
      mobile: [''],
      email: [''],
      residenceYears: [0]
    });
  }

  ngOnInit(): void {
    this.loadData();
  }

  // 按下查詢
  onSearch(): void {
    this.pageIndex = 0; // 查詢時回到第一頁
    this.loadData(this.pageIndex, this.pageSize);
  }

  // 按下清除
  onClear(): void {
    this.searchForm.reset({ residenceYears: 0 });
    this.onSearch();
  }

  // 載入資料
  loadData(pageIdx: number = 0, pageSize: number = 5) {
    // 1. 更新本地變數 (讓 HTML 分頁器同步)
    this.pageIndex = pageIdx;
    this.pageSize = pageSize;

    // 2. 呼叫 API (注意 API 頁碼是 1 開始)
    const apiPageNumber = (this.pageIndex || 0) + 1;

    console.log(`查詢: 第 ${apiPageNumber} 頁, 每頁 ${this.pageSize} 筆`);

    this.customerService.listCustomer(apiPageNumber, this.pageSize).subscribe({
      next: (res: any) => {
        if (res && res.TRANRS) {
          // 接資料列表
          this.dataSource.data = res.TRANRS.items || [];

          // ★★★ 接總筆數 (修正重點：這裡要有 totalItems 變數承接 totalCount) ★★★
          this.totalItems = res.TRANRS.totalCount || 0;

          console.log('資料載入成功，總筆數:', this.totalItems);
        } else {
          this.dataSource.data = [];
          this.totalItems = 0;
        }
      },
      error: (err) => {
        console.error('API 錯誤', err);
        this.dataSource.data = [];
        this.totalItems = 0;
      }
    });
  }

  // 分頁切換事件
  onPageChange(event: PageEvent) {
    this.loadData(event.pageIndex, event.pageSize);
  }

  private showToast(message: string, panelClass: string) {
    this.snackBar.open(message, '✕', {
      duration: 3000,
      horizontalPosition: 'left',
      verticalPosition: 'bottom',
      panelClass: [panelClass]
    });
  }
}
