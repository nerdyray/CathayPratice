// 導入 Angular 測試工具
import { ComponentFixture, TestBed } from '@angular/core/testing';

// 導入要測試的 Cif001 組件
import { Cif001 } from './cif001';

/**
 * 針對 Cif001 組件的測試套件。
 * `describe` 函數用於定義一個測試套件，包含一系列相關的測試。
 */
describe('Cif001', () => {
  let component: Cif001; // 聲明組件實例變量
  let fixture: ComponentFixture<Cif001>; // 聲明組件的測試夾具

  /**
   * 在每個測試之前執行。
   * `beforeEach` 用於配置測試模組，例如導入組件、服務等。
   */
  beforeEach(async () => {
    // 配置測試模組
    await TestBed.configureTestingModule({
      // 導入要測試的組件
      imports: [Cif001]
    })
    .compileComponents(); // 編譯組件的模板和樣式

    // 創建組件的測試夾具
    fixture = TestBed.createComponent(Cif001);
    // 獲取組件的實例
    component = fixture.componentInstance;
    // 等待組件穩定，即所有異步操作完成
    await fixture.whenStable();
  });

  /**
   * 測試案例：應該創建組件。
   * 驗證 Cif001 組件是否成功被實例化。
   */
  it('should create', () => {
    // 斷言組件實例是否存在 (即是否成功創建)
    expect(component).toBeTruthy();
  });
});
