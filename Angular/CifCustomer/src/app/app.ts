import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { LoaderService } from './services/loader';

/**
 * App 組件
 * 這是應用程式的根組件，作為整個應用的主框架或外殼。
 * 它通常包含一個 RouterOutlet，用於顯示當前路由對應的組件。
 */
@Component({
  selector: 'app-root', // 組件的 CSS 選擇器，通常在 index.html 中使用
  standalone: true,    // 標記為獨立組件
  imports: [
    CommonModule,      // 提供通用指令，如 *ngIf, *ngFor
    RouterOutlet,      // <router-outlet> 指令，用於渲染路由組件
    RouterLink,        // [routerLink] 指令，用於導航
    // --- Angular Material 模組 ---
    MatSidenavModule,  // 側邊導航容器模組
    MatListModule,     // 列表模組，用於導航菜單
    MatIconModule      // 圖標模組
    // 注意：如此處直接導入 Cif001, ConfirDelete 等組件是多餘的，
    // 因為它們是透過路由加載的，而不是直接在 app.html 模板中使用。
  ],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App {
  //建構子
  constructor(public loaderService: LoaderService) {
  }
  // 應用程式的標題，可以在模板中或其他地方使用
  title = 'CifCustomer';
}
