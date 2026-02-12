/**
 * main.ts
 * 這是 Angular 應用程式的主要入口點。
 * 它負責配置和啟動根組件 App。
 */
import { bootstrapApplication } from '@angular/platform-browser';
import { App } from './app/app';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideRouter } from '@angular/router';
import { routes } from './app/app.routes';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { Interceptor } from './app/comm/interceptor';

// bootstrapApplication 用於啟動一個獨立的 Angular 組件作為應用程式。
bootstrapApplication(App, {
  // `providers` 陣列用於配置整個應用程式可用的服務和功能。
  providers: [
    // 1. 設置路由：將 `routes` 陣列中定義的路由規則提供給應用程式。
    provideRouter(routes),

    // 2. 啟用動畫：為 Angular Material 的動畫效果提供異步加載支持。
    provideAnimationsAsync(),

    // 3. 設置 HttpClient 並註冊攔截器：
    //    - provideHttpClient()：提供可在整個應用中注入和使用的 HttpClient 服務。
    //    - withInterceptors([Interceptor])：註冊一個或多個 HTTP 攔截器，所有 HttpClient 發出的請求都會經過它。
    provideHttpClient(withInterceptors([Interceptor]))
  ]
  // 捕獲並在控制台記錄應用程式啟動過程中可能發生的任何錯誤。
}).catch(err => console.error(err));
