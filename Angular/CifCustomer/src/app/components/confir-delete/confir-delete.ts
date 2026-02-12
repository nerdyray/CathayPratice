import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

/**
 * 定義傳入 ConfirDelete 對話框的數據結構。
 */
export interface DialogData {
  idNum: string;       // 客戶身分證號碼
  chineseName: string; // 客戶中文姓名
}

/**
 * ConfirDelete 組件
 * 一個 Material Design 對話框，用於向用戶顯示一個確認刪除的提示。
 * 它會接收要刪除的客戶資料，並提供「取消」和「確認刪除」的選項。
 */
@Component({
  selector: 'app-confir-delete',
  templateUrl: './confir-delete.html',
  styleUrls: ['./confir-delete.css'],
  standalone: true,
  imports: [
    CommonModule,
    MatDialogModule,
    MatButtonModule,
    MatIconModule
  ]
})
export class ConfirDelete {
  /**
   * 組件的構造函數
   * @param dialogRef - 對當前對話框的引用，可用於控制對話框 (例如關閉)。
   * @param data - 透過 `MAT_DIALOG_DATA` 注入的數據，包含了從父組件傳遞過來的客戶資訊。
   */
  constructor(
    public dialogRef: MatDialogRef<ConfirDelete>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) { }

  /**
   * 處理「取消」按鈕的點擊事件。
   * 這個方法會關閉對話框，並返回 `false` 給打開此對話框的組件，表示用戶取消了操作。
   */
  onNoClick(): void {
    this.dialogRef.close(false);
  }
}
