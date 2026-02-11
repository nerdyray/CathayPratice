import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule, MatDialogClose, MatDialogActions, MatDialogContent } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
export interface DialogData {
  idNum: string;
  chineseName: string;
}
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
  imports: [MatIconModule, MatDialogContent, MatDialogActions, MatDialogClose, MatButtonModule, MatDialogModule, CommonModule,
    MatDialogModule,
    MatButtonModule,
    MatIconModule]
})
export class ConfirDelete {
  /**
   * 構造函數，注入 MatDialogRef 和 MAT_DIALOG_DATA。
   * @param dialogRef 對話框的引用，用於關閉對話框。
   * @param data 傳入對話框的數據，例如要刪除的客戶的 idNum 和 chineseName。
   */
  constructor(
    public dialogRef: MatDialogRef<ConfirDelete>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) { }

  onNoClick(): void {
    this.dialogRef.close(false);
  }
}
