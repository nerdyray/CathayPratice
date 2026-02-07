// 導入 Angular 測試工具 TestBed
import { TestBed } from '@angular/core/testing';

// 導入要測試的 CustomerService
import { CustomerService } from './customer-service';

/**
 * 針對 CustomerService 的測試套件。
 * `describe` 函數用於定義一個測試套件，包含一系列相關的測試。
 */
describe('CustomerService', () => {
  let service: CustomerService; // 聲明服務實例變量

  /**
   * 在每個測試之前執行。
   * `beforeEach` 用於配置測試模組，例如提供服務。
   */
  beforeEach(() => {
    // 配置測試模組
    TestBed.configureTestingModule({});
    // 從測試模組中注入 CustomerService 實例
    service = TestBed.inject(CustomerService);
  });

  /**
   * 測試案例：應該創建服務。
   * 驗證 CustomerService 是否成功被實例化。
   */
  it('should be created', () => {
    // 斷言服務實例是否存在 (即是否成功創建)
    expect(service).toBeTruthy();
  });
});
