// 匯入 Angular 測試所需的核心模組
import { ComponentFixture, TestBed } from '@angular/core/testing';

// 匯入要被測試的元件
import { Store001 } from './store001';

// `describe` 是一個測試集 (Test Suite)，用來群組相關的測試案例
describe('Store001', () => {
  let component: Store001; // 宣告要測試的元件實例
  let fixture: ComponentFixture<Store001>; // 宣告一個測試夾具，用來幫助我們與元件互動

  // `beforeEach` 會在每個 `it` 測試案例執行前都先執行一次
  beforeEach(async () => {
    // `TestBed` 是 Angular 測試的主要工具，可以建立一個模擬的 Angular 環境
    await TestBed.configureTestingModule({
      imports: [Store001] // 匯入要測試的獨立元件
    })
    .compileComponents(); // 編譯元件的樣板和樣式

    // 透過 TestBed 建立一個 Store001 元件的實例
    fixture = TestBed.createComponent(Store001);
    // 從夾具中取得元件的實例
    component = fixture.componentInstance;
    // 等待非同步操作完成並觸發變更偵測
    await fixture.whenStable();
  });

  // `it` 是一個測試案例 (Test Case)，描述一個獨立的測試情境
  it('should create', () => {
    // `expect` 是斷言，用來檢查某個值是否符合預期
    // `toBeTruthy()` 會檢查 component 是否存在 (不是 null 或 undefined)
    expect(component).toBeTruthy();
  });
});
