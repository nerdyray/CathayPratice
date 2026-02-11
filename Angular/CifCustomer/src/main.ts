import { bootstrapApplication } from '@angular/platform-browser';
import { App } from './app/app';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideRouter } from '@angular/router';
import { routes } from './app/app.routes';

/**
 * 應用程式的入口點。
 * 使用 `bootstrapApplication` 啟動 Angular 獨立應用程式。
 */
bootstrapApplication(App, {
  // 為應用程式提供服務配置
  providers: [
    // 啟用路由功能，使用在 `./app/app.routes.ts` 中定義的路由
    provideRouter(routes), // <--- 必須加上這一行！
    // 啟用異步動畫支持
    provideAnimationsAsync()
  ]
}).catch(err => console.error(err)); // 捕獲並記錄應用程式啟動時可能發生的錯誤
