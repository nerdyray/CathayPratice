import { Injectable } from '@angular/core';
import { BehaviorSubject, delay } from 'rxjs';

@Injectable({
  providedIn: 'root' // 確保這是全域單例 (Global Singleton)
})
export class LoaderService {
  // 內部的計數器
  private count = 0;
  // 用於廣播狀態的 Subject，預設為 false (不顯示)
  private loadingSubject$ = new BehaviorSubject<boolean>(false);
  // 供外部組件訂閱的 Observable
  // 使用 $ 結尾是 RxJS 的慣例
  isLoading$ = this.loadingSubject$.asObservable().pipe(
    delay(0)
  );
  show() {
    this.count++;
    this.updateStatus();
  }

  hide() {
    this.count = Math.max(0, this.count - 1); // 確保不會變成負數
    this.updateStatus();
  }

  private updateStatus() {
    const isVisible = this.count > 0;
    // 只要計數器 > 0，就廣播「顯示」信號
    this.loadingSubject$.next(this.count > 0);

    console.log(`[Debug] 計數器啟動: ${this.count}`);

  }
}
