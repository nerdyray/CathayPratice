import { MWHEADER } from './../../../interface/Q003Tranrs';
import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

// Angular Material Modules
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

// Components & Services
import { Search001 } from '../../comm/search001/search001';
import { CustomerService } from '../../../services/customerService'; // 假設你的 Service 路徑
import { Data } from '../../../interface/Q002Tranrq'; // 假設你的資料介面
import { Router } from '@angular/router';

const MATERIAL_MODULES = [
  MatTableModule,
  MatPaginatorModule,
  MatSnackBarModule,
  MatIconModule,
  MatButtonModule,
  MatTableModule,
  MatPaginatorModule,
  MatButtonModule
];

@Component({
  selector: 'app-cif001',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule, Search001, ...MATERIAL_MODULES,],
  templateUrl: './cif001.html',
  styleUrl: './cif001.css',
})
export class Cif001 implements OnInit {

  // 1. 原始資料備份 (從後端撈回來的完整資料)
  originalData: Data[] = [];

  // 2. 表格資料來源 (MatTableDataSource 內建了過濾和分頁功能)
  dataSource = new MatTableDataSource<Data>([]);

  // 表格欄位
  displayedColumns: string[] = ['idNum', 'chineseName', 'gender', 'education', 'mobile', 'email', 'year', 'actions'];

  // 分頁器
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  totalItems = 0;

  constructor(
    private router: Router, // 2. 注入 Router
    private customerService: CustomerService,
    private snackBar: MatSnackBar,
    private cdr: ChangeDetectorRef

  ) { }

  ngOnInit(): void {
    // 初始化時呼叫後端 API 載入資料
    this.loadData();
  }

  /**
   * 從後端 API 取得資料
   * 注意：為了讓前端能做搜尋，這裡假設 API 回傳的是「全部資料」或「大筆資料」
   */
  loadData() {
    // 呼叫 Service (這裡參數傳 1, 1000 假設一次撈取較多資料，視實際需求調整)
    this.customerService.listCustomer(1, 1000, {}).subscribe({
      next: (res: any) => {
        if (res && res.TRANRS && res.TRANRS.items) {
          // 1. 保存原始資料
          this.originalData = res.TRANRS.items;

          // 2. 初始化表格資料
          this.dataSource.data = this.originalData;
          this.dataSource.paginator = this.paginator; // 綁定分頁器
          this.totalItems = this.originalData.length;

          console.log('資料載入成功:', this.originalData);
        } else {
          this.dataSource.data = [];
          this.originalData = [];
        }
      },
      error: (err) => {
        console.error('API Error:', err);
        this.showToast('資料載入失敗', 'error-snackbar');
      }
    });
  }
  /**
    * 點擊修改：將整筆資料透過路由狀態傳遞到編輯頁
    */
  onEdit(element: any): void {
    // 注意這裡要對應路由中的 'cif/edit'
    this.router.navigate(['/cif/edit'], { state: { customerData: element } });
  }
  onDelete(orderId: number): void {
    console.log('點擊刪除:', orderId);
    this.customerService.deleteCustomer(orderId).subscribe({
      next: (res: any) => {
        if (res.MWHEADER.RETURNCODE !== '0000') {
          this.showToast(res.MWHEADER.RETURNDESC, res.MWHEADER.RETURNCODE);
          return;
        }
        this.loadData();
      }
    })
  }
  /**
   * 接收 Search001 的搜尋事件 (前端過濾)
   */
  onSearchFromChild(criteria: any): void {
    console.log('前端過濾條件:', criteria);

    // 根據 criteria 過濾 originalData
    const filteredResult = this.originalData.filter(item => {
      // 1. 身分證 (模糊)
      const matchId = criteria.idNum ? (item.idNum || '').includes(criteria.idNum) : true;

      // 2. 姓名 (模糊)
      const matchName = criteria.chineseName ? (item.chineseName || '').includes(criteria.chineseName) : true;

      // 3. 性別 (精確，若選 "不拘" 則 criteria.gender 為空字串，回傳 true)
      const matchGender = criteria.gender ? item.gender === criteria.gender : true;

      // 4. 學歷 (模糊，假設資料庫也是存字串)
      const matchEdu = criteria.education ? (item.education || '').includes(criteria.education) : true;

      // 5. 手機 (模糊)
      const matchMobile = criteria.mobile ? (item.mobile || '').includes(criteria.mobile) : true;

      // 6. Email (模糊)
      const matchEmail = criteria.email ? (item.email || '').includes(criteria.email) : true;

      // 7. 現居年限 (大於等於)
      // 注意：要先確認 item.year 是否為數字
      const matchYear = (criteria.year !== null && criteria.year !== undefined) ?
        Number(item.year || 0) >= criteria.year : true;

      return matchId && matchName && matchGender && matchEdu && matchMobile && matchEmail && matchYear;
    });

    // 更新表格資料
    this.dataSource.data = filteredResult;

    // 如果有分頁器，搜尋後要跳回第一頁
    if (this.paginator) {
      this.paginator.firstPage();
    }

    // 查無資料提示
    if (filteredResult.length === 0) {
      this.showToast('查無符合條件的資料', 'warning-snackbar');
    }
  }

  private showToast(message: string, panelClass: string) {
    this.snackBar.open(message, '關閉', {
      duration: 3000,
      panelClass: [panelClass]
    });
  }
}
