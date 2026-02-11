// 導入 Angular 測試工具 TestBed
import { TestBed } from '@angular/core/testing';
// 導入 HTTP 攔截器類型
import { HttpInterceptorFn } from '@angular/common/http';

// 導入要測試的攔截器函數
import { interceptorInterceptor } from './interceptor-interceptor';

/**
 * 針對 interceptorInterceptor 攔截器函數的測試套件。
 * `describe` 函數用於定義一個測試套件，包含一系列相關的測試。
 */
describe('interceptorInterceptor', () => {
  // 定義一個測試用的 HTTP 攔截器函數，用於在測試環境中執行實際的攔截器
  const interceptor: HttpInterceptorFn = (req, next) => 
    TestBed.runInInjectionContext(() => interceptorInterceptor(req, next));

  /**
   * 在每個測試之前執行。
   * `beforeEach` 用於配置測試模組，例如初始化 TestBed。
   */
  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  /**
   * 測試案例：應該被創建。
   * 驗證攔截器函數是否成功被實例化。
   */
  it('should be created', () => {
    expect(interceptor).toBeTruthy();
  });
});
