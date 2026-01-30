import { Routes } from '@angular/router';
import { Store001 } from './components/list/store001/store001';
import { Edit001 } from './components/form/edit001/edit001';
import { Create001 } from './components/form/create001/create001';

/**
 * `routes` 是一個陣列，用來定義整個應用程式的路由規則。
 * 每一條規則都是一個物件，描述了「哪個網址」對應到「哪個元件」。
 */
export const routes: Routes = [
  // 當網址是 /store/list 時，顯示 Store001 (查詢列表) 元件
  { path: 'store/list', component: Store001 },
  // 當網址是 /store/update/某個ID (例如 /store/update/S001) 時，顯示 Edit001 (編輯) 元件
  // `:storeId` 是一個路由參數，我們可以在 Edit001 元件中取得這個 ID 值
  { path: 'store/update/:storeId', component: Edit001 },
  // 當網址是 /store/create 時，顯示 Create001 (新增) 元件
  { path: 'store/create', component: Create001 },
  // `**` 是一個萬用字元路徑，代表「所有不符合上面任何規則的網址」
  // `redirectTo` 表示要重新導向到 'store/list'
  // `pathMatch: 'full'` 表示必須是完整的路徑都匹配不到，才會觸發此重新導向
  // 效果：當使用者輸入任何無效網址時，都會自動跳轉到店家列表頁
  { path: '**', redirectTo: 'store/list', pathMatch: 'full' },

];
