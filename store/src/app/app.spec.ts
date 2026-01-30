import { TestBed } from '@angular/core/testing';
import { App } from './app';

// 測試根元件 App
describe('App', () => {
  // 在每個測試案例執行前，先設定好測試環境
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [App], // 匯入要測試的 App 元件
    }).compileComponents();
  });

  // 測試案例 1: 檢查 App 元件是否能被成功建立
  it('should create the app', () => {
    const fixture = TestBed.createComponent(App);
    const app = fixture.componentInstance;
    // 斷言：期望 app 實例存在
    expect(app).toBeTruthy();
  });

  // 測試案例 2: 檢查標題是否被正確渲染
  // 注意：這個測試案例可能會因為 app.html 的內容變更而失敗。
  // 它預期在 HTML 中找到一個 <h1> 標籤並包含 'Hello, store' 文字。
  it('should render title', async () => {
    const fixture = TestBed.createComponent(App);
    // 等待元件穩定 (例如，非同步操作完成)
    await fixture.whenStable();
    // 取得渲染後的 DOM 元素
    const compiled = fixture.nativeElement as HTMLElement;
    // 斷言：期望找到一個 h1 標籤，且其內容包含 'Hello, store'
    expect(compiled.querySelector('h1')?.textContent).toContain('Hello, store');
  });
});
