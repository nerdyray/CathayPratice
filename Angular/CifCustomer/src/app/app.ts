// 導入 Component 裝飾器和 signal 函數，用於創建組件和響應式狀態管理
import { Component, signal } from '@angular/core';
// 導入 RouterOutlet，用於在路由匹配時顯示組件
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
// 導入 Create001 組件，這是表單創建功能
import { Create001 } from "./components/form/create001/create001";
// 導入 FormsModule，用於模板驅動表單
import { FormsModule } from '@angular/forms';
// 導入 Cif001 組件，這是客戶列表顯示功能
import { Cif001 } from "./components/list/cif001/cif001";
import { Edit001 } from './components/form/edit001/edit001';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatSidenavModule } from '@angular/material/sidenav';

/**
 * 應用程式的根組件。
 * `@Component` 裝飾器定義了這個類是一個 Angular 組件，並提供其元數據。
 */
@Component({
  selector: 'app-root', // CSS 選擇器，用於在 HTML 中識別和使用此組件
  // 導入其他模組或組件，使其模板中可使用
  imports: [RouterOutlet, Create001, FormsModule, Cif001, Edit001,
    RouterLink,
    RouterLinkActive,
    MatSidenavModule, // <--- 關鍵：一定要有這個
    MatListModule,    // <--- 關鍵：列表樣式
    MatIconModule],
  templateUrl: './app.html', // 組件的 HTML 模板文件路徑
  styleUrl: './app.css' // 組件的 CSS 樣式文件路徑
})
export class App {
  // 定義一個只讀的響應式信號（signal），用於存儲應用程式的標題。
  // `protected` 關鍵字表示這個屬性可以在類內部和子類中訪問。
  // `readonly` 表示該信號的引用不能被重新分配。
  protected readonly title = signal('CifCustomer');
}
