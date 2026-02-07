// 導入 Angular 應用程式的配置類型和相關功能
import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
// 導入 Angular 路由功能
import { provideRouter } from '@angular/router';

// 從當前目錄導入應用程式的路由定義
import { routes } from './app.routes';

/**
 * 應用程式的配置物件。
 * 定義了應用程式啟動時所需的所有提供者 (providers)。
 */
export const appConfig: ApplicationConfig = {
  // 提供者陣列，包含應用程式級別的服務
  providers: [
    // 提供瀏覽器全局錯誤監聽器，用於捕獲和處理應用程式中的未捕獲錯誤
    provideBrowserGlobalErrorListeners(),
    // 提供應用程式的路由功能，使用在 app.routes.ts 中定義的路由
    provideRouter(routes)
  ]
};
