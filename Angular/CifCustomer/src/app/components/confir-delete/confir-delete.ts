
import { Component, Inject } from '@angular/core';
// 導入 MAT_DIALOG_DATA, MatDialogRef, MatDialogContent, MatDialogActions, MatDialogClose
// 這些是 Angular Material 對話框相關的模組和服務
import { MAT_DIALOG_DATA, MatDialogRef, MatDialogContent, MatDialogActions, MatDialogClose, MatDialogModule } from '@angular/material/dialog';
// 導入 MatIcon 模組，用於顯示 Material Icon
import { MatIcon, MatIconModule } from "@angular/material/icon";
import { MatButtonModule } from '@angular/material/button';

/**
 * ConfirDelete 組件，用於顯示一個確認刪除的對話框。
 * 它接收要刪除的客戶資料，並提供取消和確認刪除的選項。
 */
@Component({
  selector: 'app-confir-delete', // 組件的 CSS 選擇器
  templateUrl: './confir-delete.html',
  styleUrls: ['./confir-delete.css'],
  standalone: true,
  // 導入對話框模板中使用的 Material 模組
  imports: [MatIconModule, MatDialogContent, MatDialogActions, MatDialogClose, MatButtonModule, MatDialogModule]
})
export class ConfirDelete {
  /**
   * 構造函數，注入 MatDialogRef 和 MAT_DIALOG_DATA。
   * @param dialogRef 對話框的引用，用於關閉對話框。
   * @param data 傳入對話框的數據，例如要刪除的客戶的 idNum 和 chineseName。
   */
  constructor(
    public dialogRef: MatDialogRef<ConfirDelete>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  /**
   * 處理「取消」按鈕點擊事件或關閉按鈕點擊事件。
   * 關閉對話框而不返回任何結果。
   */
  onNoClick(): void {
    this.dialogRef.close();
  }
}
