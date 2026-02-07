// 導入 Angular 測試工具 TestBed
import { TestBed } from '@angular/core/testing';
// 導入要測試的根組件 App
import { App } from './app';

/**
 * 針對 App 根組件的測試套件。
 * `describe` 函數用於定義一個測試套件，包含一系列相關的測試。
 */
describe('App', () => {
  /**
   * 在每個測試之前執行。
   * `beforeEach` 用於配置測試模組，例如導入組件、服務等。
   */
  beforeEach(async () => {
    // 配置測試模組
    await TestBed.configureTestingModule({
      // 導入要測試的組件
      imports: [App],
    }).compileComponents(); // 編譯組件的模板和樣式
  });

  /**
   * 測試案例：應該創建應用程式。
   * 驗證 App 組件是否成功被實例化。
   */
  it('should create the app', () => {
    // 創建 App 組件的實例
    const fixture = TestBed.createComponent(App);
    // 獲取組件的實例
    const app = fixture.componentInstance;
    // 斷言組件實例是否存在 (即是否成功創建)
    expect(app).toBeTruthy();
  });

  /**
   * 測試案例：應該渲染標題。
   * 驗證 App 組件模板中是否包含特定的標題文字。
   */
  it('should render title', async () => {
    // 創建 App 組件的實例
    const fixture = TestBed.createComponent(App);
    // 等待組件穩定，即所有異步操作完成
    await fixture.whenStable();
    // 獲取組件的 HTML 元素
    const compiled = fixture.nativeElement as HTMLElement;
    // 斷言 h1 元素中是否包含 'Hello, CifCustomer' 文字
    expect(compiled.querySelector('h1')?.textContent).toContain('Hello, CifCustomer');
  });
});
