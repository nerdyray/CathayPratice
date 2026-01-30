import { TestBed } from '@angular/core/testing';
import { StoreService } from './storeService';

// 測試 StoreService
describe('StoreService', () => { // 測試集名稱通常會對應到被測試的類別
  let service: StoreService;

  // 在每個測試案例執行前，先設定好測試環境
  beforeEach(() => {
    // TestBed.configureTestingModule 用來建立一個測試模組
    // 對於 Service 的基本測試，通常不需要特別的設定
    TestBed.configureTestingModule({});
    // 透過 TestBed 的依賴注入系統來取得 StoreService 的實例
    service = TestBed.inject(StoreService);
  });

  // 測試案例: 檢查 StoreService 是否能被成功建立
  it('should be created', () => {
    // 斷言：期望 service 實例存在
    expect(service).toBeTruthy();
  });
});
