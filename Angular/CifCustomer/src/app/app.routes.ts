// 導入 Angular 路由模組中的 Routes 類型
import { Routes } from '@angular/router';
import { Cif001 } from './components/list/cif001/cif001';
import { Edit001 } from './components/form/edit001/edit001';
import { Search001 } from './components/comm/search001/search001';
import { Create001 } from './components/form/create001/create001';

/**
 * 應用程式的路由定義。
 * 這是一個 Routes 類型的陣列，用於配置應用程式中的所有路由。
 * 目前為空，表示尚未定義任何特定的應用程式級別路由。
 * 路由通常用於將 URL 路徑映射到不同的組件。
 */
export const routes: Routes = [
  { path: 'cif/create', component: Create001 },
  { path: 'cif/edit', component: Edit001 },
  { path: 'cif/search001', component: Cif001 },
  { path: '**', redirectTo: 'cif/search001', pathMatch: 'full' }, // 預設跳轉到列表頁


];
