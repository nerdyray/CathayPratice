// 匯入 Angular 的核心功能
import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';

// 匯入我們定義的路由設定
import { routes } from './app.routes';

/**
 * `appConfig` 是整個 Angular 應用程式的根設定物件。
 * 它告訴 Angular 如何組裝應用程式，以及需要提供哪些核心服務。
 */
export const appConfig: ApplicationConfig = {
  // `providers` 陣列用來註冊整個應用程式「共用」的服務。
  // 在這裡註冊的服務，在應用程式的任何地方都可以被注入和使用。
  providers: [
    // 註冊 HttpClient 相關服務，讓我們可以在 Service 中使用 HttpClient 來發送網路請求。
    provideHttpClient(),
    // 註冊一個全域的錯誤監聽器，可以捕捉並處理應用程式中未被捕獲的錯誤。
    provideBrowserGlobalErrorListeners(),
    // 註冊路由服務，並將我們定義的 `routes` 陣列傳遞進去，告訴 Angular 網站的網址結構。
    provideRouter(routes)
  ]
};
