// 導入 Angular 路由模組中的 Routes 類型
import { Routes } from '@angular/router';
// 導入各個頁面組件
import { Cif001 } from './components/list/cif001/cif001';
import { Edit001 } from './components/form/edit001/edit001';
import { Create001 } from './components/form/create001/create001';

/**
 * 應用程式的路由定義 (Routes)
 * 這個陣列配置了 URL 路徑與 Angular 組件之間的映射關係。
 */
export const routes: Routes = [
  // 當 URL 為 /cif/create 時，渲染 Create001 組件
  { path: 'cif/create', component: Create001 },

  // 當 URL 為 /cif/edit 時，渲染 Edit001 組件
  { path: 'cif/edit', component: Edit001 },

  // 當 URL 為 /cif/search001 時，渲染 Cif001 組件 (客戶列表頁)
  { path: 'cif/search001', component: Cif001 },

  // 萬用字元 (wildcard) 路由，用於處理所有其他不匹配的 URL
  // path: '**' 匹配任何不符合上述規則的路徑
  // redirectTo: 'cif/search001' 將用戶重定向到客戶列表頁
  // pathMatch: 'full' 要求 URL 必須完全匹配 (在這裡，它匹配所有)
  { path: '**', redirectTo: 'cif/search001', pathMatch: 'full' },
];
