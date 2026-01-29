import { Routes } from '@angular/router';
import { Store001 } from './components/list/store001/store001';
import { Edit001 } from './components/form/edit001/edit001';

export const routes: Routes = [
  { path: 'store/list', component: Store001 },
  { path: '', redirectTo: 'store/list', pathMatch: 'full' },
  { path: 'store/update/:storeId', component: Edit001 },
];
