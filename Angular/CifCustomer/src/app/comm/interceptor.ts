// 導入 HttpInterceptorFn 類型，這是 Angular HTTP 攔截器函數的簽名
import { HttpInterceptorFn } from '@angular/common/http';

/**
 * Interceptor 是一個 HTTP 請求攔截器函數。
 * 它可以在 HTTP 請求發送出去之前或響應到達應用程式之前，對其進行修改或處理。
 *
 * @param req 傳入的 HTTP 請求物件。
 * @param next 一個函數，調用它會將請求傳遞給鏈中的下一個攔截器或最終的後端處理器。
 * @returns 返回一個 Observable，包含被修改或原始的 HTTP 事件流。
 */
export const Interceptor: HttpInterceptorFn = (req, next) => {
  // 在這裡可以對請求進行修改，例如添加認證 Token、日誌記錄等。
  // 目前這個攔截器只是簡單地將請求傳遞給下一個處理器，沒有做任何修改。
  return next(req);
};
