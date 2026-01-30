// 匯入 Angular 核心模組
import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

// 匯入會在這個根元件中使用的其他元件
// 注意：在獨立元件(Standalone)的架構下，如果元件是透過路由(Router)載入的，
// 則不一定需要在根元件中匯入它們。此處的匯入可能是為了其他目的或可被簡化。
import { Create001 } from "./components/form/create001/create001";
import { Store001 } from "./components/list/store001/store001";
import { Edit001 } from "./components/form/edit001/edit001";

/**
 * 這是 Angular 應用程式的「根元件」(Root Component)。
 * 它是整個元件樹的最頂端，所有其他的元件都在它的內部。
 */
@Component({
  selector: 'app-root', // 在 index.html 中，就是透過 <app-root></app-root> 來啟動整個應用程式的
  imports: [
    RouterOutlet, // 匯入路由插座，用來顯示與目前網址匹配的元件
    Create001,
    Store001,
    Edit001
  ],
  templateUrl: './app.html', // 指定 HTML 樣板
  styleUrl: './app.css'      // 指定 CSS 樣式
})
export class App {
  // `signal` 是 Angular v16+ 引入的新功能，用來建立一個響應式的值。
  // 當 signal 的值改變時，所有用到它的地方都會自動更新。
  // `protected readonly` 確保這個屬性只能在類別內部或樣板中被讀取。
  protected readonly title = signal('store');
}
