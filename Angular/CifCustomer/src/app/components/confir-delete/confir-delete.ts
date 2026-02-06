import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';



@Component({
  selector: 'app-delete-confirm',
  template: `
    <div class="dialog-header">
      <button mat-icon-button class="close-btn" (click)="onNoClick()">
        <mat-icon>close</mat-icon>
      </button>
    </div>

    <mat-dialog-content class="dialog-content">
      <h1 class="warning-title">確定要刪除 【 {{data.idNum}} {{data.chineseName}} 】 嗎？</h1>
      <p class="warning-subtitle">刪除的客戶資料無法復原，請確認是否刪除。</p>
    </mat-dialog-content>

    <mat-dialog-actions align="center" class="dialog-actions">
      <button mat-button class="btn-cancel" (click)="onNoClick()">取消</button>
      <button mat-flat-button color="warn" class="btn-confirm" [mat-dialog-close]="true">確認</button>
    </mat-dialog-actions>
  `,
  styles: [`
    .dialog-header { display: flex; justify-content: flex-end; }
    .dialog-content { text-align: center; padding: 20px 40px; }
    .warning-title { color: #d32f2f; font-size: 24px; font-weight: bold; margin-bottom: 16px; }
    .warning-subtitle { color: #666; font-size: 16px; }
    .dialog-actions { padding-bottom: 30px; gap: 20px; }
    .btn-cancel { border: 1px solid #28a745; color: #28a745; width: 120px; border-radius: 20px; }
    .btn-confirm { background-color: #f44336; width: 120px; border-radius: 20px; }
  `]
})
export class ConfirDelete {
  constructor(
    public dialogRef: MatDialogRef<ConfirDelete>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
