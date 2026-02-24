import { Q002Tranrq, Q002TranrqPage, Q002TranrqSortInfo } from './../../../interface/Q002Tranrq';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { ConfirDelete } from '../../confir-delete/confir-delete';
import { MWHEADER, TRANRS } from './../../../interface/Q003Tranrs';
import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

// Angular Material Modules
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatSort, MatSortModule } from '@angular/material/sort';

// Components & Services
import { Search001 } from '../../comm/search001/search001';
import { CustomerService } from '../../../services/customerService';
import { Data } from '../../../interface/Q002Tranrq';
import { Router } from '@angular/router';
import { Education } from '../../../interface/Q004Tranrs';

// 集合所有需要使用的 Angular Material 模組，方便管理
const MATERIAL_MODULES = [
  MatTableModule,
  MatPaginatorModule,
  MatSnackBarModule,
  MatIconModule,
  MatButtonModule,
  MatTableModule,
  MatPaginatorModule,
  MatButtonModule,
  MatDialogModule,
  MatSortModule
];
/**
 * Cif001 組件
 * 負責顯示客戶列表、處理分頁、搜尋、編輯和刪除功能。
 */
@Component({
  selector: 'app-cif001',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule, Search001, ...MATERIAL_MODULES,],
  templateUrl: './cif001.html',
  styleUrl: './cif001.css',
})
export class Cif001 implements OnInit {
  length = 0;
  pageSize = 10;
  pageIndex = 0;
  pageSizeOptions = [5, 10, 25];

  hidePageSize = false;
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  disabled = false;

  pageEvent: PageEvent | undefined;
  page: Q002TranrqPage = {
    pageNumber: 0,
    pageSize: 10
  }
  sortInfo: Q002TranrqSortInfo = {
    sortBy: 'asc',
    sortColumn: 'idNum'
  }

  educatinOpt: Education[] = [];

  eduList: string[] = [];

  // 用於備份從後端獲取的完整原始數據，以便在前端進行過濾
  // originalData: Data[] = [];


  dataSource = new MatTableDataSource<Data>([]);

  // 定義表格要顯示的欄位名稱和順序
  displayedColumns: string[] = ['idNum', 'chineseName', 'gender', 'education', 'mobile', 'email', 'address1', 'zipCode1', 'year', 'actions'];

  // 透過 @ViewChild 獲取模板中對分頁器元件的引用
  @ViewChild(Search001) form!: Search001;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  /**
   * 組件的構造函數
   * @param dialog 用於打開 Material Design 對話框的服務
   * @param router 用於程式化導航的服務
   * @param customerService 用於與客戶相關 API 互動的服務
   * @param snackBar 用於顯示短暫訊息（Toast）的服務
   * @param cdr 用於手動觸發變更檢測的服務
   */
  constructor(
    private dialog: MatDialog,
    private router: Router,
    private customerService: CustomerService,
    private snackBar: MatSnackBar,
    private cdr: ChangeDetectorRef

  ) { }
  ngAfterViewInit() {
    // this.dataSource.sort = this.sort;
    this.sort.sortChange.subscribe(() => {
      // 排序一變動，通常會強迫回到第一頁
      this.pageIndex = 0;
      if (this.paginator) {
        this.paginator.pageIndex = 0;
      }

      // 2. 更新你預先包裝好的 sortInfo 物件
      this.sortInfo = {
        sortColumn: this.sort.active,    // 目前點擊的欄位名稱 (例如: "idNum")
        sortBy: this.sort.direction     // "asc", "desc" 或 "" (空字串)
      };

      // 3. 重新抓取資料
      this.fetchData();
    });

    // 初始載入
    this.fetchData();

  }

  /**
   * Angular 生命週期鉤子，在組件初始化時調用。
   */
  ngOnInit(): void {
    this.customerService.selectOpt().subscribe({
      next: (res) => {
        this.educatinOpt = res.TRANRS.education;
        this.eduList = res.TRANRS.education.map(edu => edu.MsgOptionMemo);
        console.log(this.eduList)
      }
    })

  }

  /**
   * 處理編輯按鈕的點擊事件。
   * 導航到編輯頁面，並透過路由狀態 (state) 傳遞該筆客戶的完整資料。
   * @param element - 表格中被點擊的該行數據對象
   */
  onEdit(element: any): void {
    // 導航到 '/cif/edit' 路由，並將 customerData 附加到 state 中
    this.router.navigate(['/cif/edit'], { state: { customerData: element } });
  }

  /**
   * 處理刪除按鈕的點擊事件。
   * 打開一個確認對話框，如果用戶確認刪除，則調用後端 API 執行刪除操作。
   * @param element - 表格中被點擊的該行數據對象
   */
  onDelete(element: any): void {
    // 打開確認刪除的對話框
    const dialogRef = this.dialog.open(ConfirDelete, {
      width: '400px',
      data: { idNum: element.idNum, chineseName: element.chineseName } // 傳遞客戶資訊給對話框顯示
    });
    // 訂閱對話框關閉後的事件
    dialogRef.afterClosed().subscribe(result => {
      // 如果用戶在對話框中點擊了「確認」(result 為 true)
      if (result) {

        // 調用 service 的 deleteCustomer 方法
        this.customerService.deleteCustomer(element.orderId).subscribe({
          next: (res: any) => {
            // 檢查後端返回的操作碼
            if (res.MWHEADER.RETURNCODE !== '0000') {
              this.showToast(res.MWHEADER.RETURNDESC, 'error-snackbar'); // 顯示後端返回的錯誤訊息
              return;
            }
            // 刪除成功後，顯示成功訊息並重新加載數據以更新表格
            this.showToast('刪除成功', 'success-snackbar');
            // this.loadData();
          },
          error: (err) => {
            console.error('API 刪除錯誤:', err);
            this.showToast('刪除失敗', 'error-snackbar');
          }
        });
      }
    });
  }

  /**
   * 從後端服務加載客戶數據。
   * 注意：目前的實現方式是獲取大量數據到前端進行過濾，這在數據量大時可能影響性能。
   * 優化方向：應改為每次搜尋或換頁時，都帶著過濾條件和分頁參數請求後端。
   */

  fetchData() {
    console.log(this.form.searchForm)
    // build search params
    const tranrq: Q002Tranrq = {
      DATA: this.form.searchForm.getRawValue(),
      PAGE: {
        pageNumber: this.pageIndex, // 抓取最新的變數值
        pageSize: this.pageSize
      },
      STOREINFO: {
        sortColumn: this.sort.active || 'idNum', // 沒選欄位時的預設排序
        sortBy: this.sort.direction
      }
    }

    this.customerService.listCustomer(tranrq).subscribe({
      next: (res: any) => {
        if (res && res.TRANRS && res.TRANRS.items) {
        }
        // 將過濾後的結果更新到表格數據源
        this.length = res.TRANRS.totalCount;
        this.dataSource.data = res.TRANRS.items;

        console.log(res.TRANRS.items);
        console.log(res.TRANRS.totalCount);

        // 如果過濾後沒有任何結果，顯示提示訊息
        if (res.TRANRS.items.length === 0) {
          this.showToast('查無符合條件的資料', 'warning-snackbar');
        }
        // 如果表格正在使用分頁器，搜尋後應將分頁器跳回第一頁
        // if (this.paginator) {
        //   this.paginator.firstPage();
        //   this.dataSource.data = res.content;
        // }
      },
      error: (err) => {
        console.error('API 錯誤:', err);
        this.showToast('資料載入失敗', 'error-snackbar');
      }
    });




  }

  getMesgMemo(msgOption: string): string {
    return this.educatinOpt.find(edu => edu.MsgOption === msgOption)?.MsgOptionMemo ?? '';
  }

  /**
   * 接收子組件 (Search001) 觸發的搜尋事件，並在前端進行數據過濾。
   * @param criteria - 從 Search001 組件傳來的搜尋條件對象
   */
  // onSearchFromChild(criteria: any): void {

  //   // 使用 Array.prototype.filter 方法，根據搜尋條件過濾 originalData
  //   const filteredResult = this.originalData.filter(item => {
  //     // 逐一檢查每個搜尋條件是否滿足
  //     const matchId = criteria.idNum ? (item.idNum || '').includes(criteria.idNum) : true;
  //     const matchName = criteria.chineseName ? (item.chineseName || '').includes(criteria.chineseName) : true;
  //     const matchGender = criteria.gender ? item.gender === criteria.gender : true;
  //     const matchEdu = criteria.education ? (item.education || '').includes(criteria.education) : true;
  //     const matchMobile = criteria.mobile ? (item.mobile || '').includes(criteria.mobile) : true;
  //     const matchEmail = criteria.email ? (item.email || '').includes(criteria.email) : true;
  //     const matchYear = (criteria.year !== null && criteria.year !== undefined) ?
  //       Number(item.year || 0) >= criteria.year : true;
  //     // 所有條件都必須為 true，該筆數據才算匹配
  //     return matchId && matchName && matchGender && matchEdu && matchMobile && matchEmail && matchYear;
  //   });

  //   // 將過濾後的結果更新到表格數據源
  //   this.dataSource.data = filteredResult;

  //   // 如果表格正在使用分頁器，搜尋後應將分頁器跳回第一頁
  //   // 如果過濾後沒有任何結果，顯示提示訊息
  //   if (filteredResult.length === 0) {
  //     this.showToast('查無符合條件的資料', 'warning-snackbar');
  //   }
  // }

  /**
   * 顯示一個 SnackBar (Toast) 訊息。
   * @param message - 要顯示的訊息文字
   * @param panelClass - 用於控制樣式的 CSS class (例如 'success-snackbar', 'error-snackbar')
  */
  private showToast(message: string, panelClass: string) {
    this.snackBar.open(message, '關閉', {
      duration: 3000, // 3 秒後自動關閉
      panelClass: [panelClass]
    });
  }
  onPageChange(event: PageEvent): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.fetchData();
    console.log(this.pageIndex);
    console.log(this.pageSize);

  }
}
