// 導入 HttpInterceptorFn 類型，這是 Angular HTTP 攔截器函數的簽名
import { HttpInterceptorFn, HttpErrorResponse } from '@angular/common/http';
import { inject } from '@angular/core';
import { catchError, delay, finalize, throwError } from 'rxjs'; // 導入 catchError 和 throwError
import { LoaderService } from '../services/loader';

/**
 * Interceptor 是一個 HTTP 請求攔截器函數。
 * 它可以在 HTTP 請求發送出去之前或響應到達應用程式之前，對其進行修改或處理。
 *
 * @param req 傳入的 HTTP 請求物件。
 * @param next 一個函數，調用它會將請求傳遞給鏈中的下一個攔截器或最終的後端處理器。
 * @returns 返回一個 Observable，包含被修改或原始的 HTTP 事件流。
 */
export const Interceptor: HttpInterceptorFn = (req, next) => {
  //共同路徑
  const baseUrl = 'http://localhost:8080/cif/';
  // 1. 注入全域讀取狀態計數器Service
  const loaderService = inject(LoaderService);
  // 2. 利用不可變性 clone 請求，並統一加上 API 前綴與 Header
  // 這樣你的身分證 Service 就不需要寫全網址
  const apiReq = req.clone({
    url: `${baseUrl}${req.url}`,
    setHeaders: {
      'Content-Type': 'application/json',
    }
  });
  // 3. 啟動讀取狀態：計數器 +1
  loaderService.show();

  return next(apiReq).pipe(
    //故意設定讓API請求顯示0.8秒
    delay(800),
    catchError((error: HttpErrorResponse) => {
      let errorMessage = '';
      if (error.error instanceof ErrorEvent) {
        // 客戶端或網絡錯誤
        errorMessage = `客戶端錯誤: ${error.error.message}`;
      } else {
        // 後端返回的錯誤響應
        errorMessage = `伺服器錯誤: ${error.status}, 訊息: ${error.message}`;
        // - 根據 error.status 顯示不同的用戶友好訊息
        // - 導航到錯誤頁面
        // - 觸發通知服務顯示彈出消息
        console.error('後端錯誤:', error);
      }
      console.error(errorMessage);
      // 重新拋出錯誤，以便服務或組件層可以進一步處理
      return throwError(() => new Error(errorMessage));
    }),
    finalize(() => {
      loaderService.hide();
    })
  );
};
