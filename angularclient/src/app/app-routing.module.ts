import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DiscListComponent } from './shop/disc-list/disc-list.component';
import { UserFormComponent } from './public/register-form/register-form.component';
import { LoginComponent } from './public/login/login.component';
import { MainPageComponent } from './public/main-page/main-page.component';
import { DiscFormComponent } from './shop/disc-form/disc-form.component';
import { CartComponent } from './shop/cart/cart.component';
import { OrderListComponent } from './shop/order-list/order-list.component';
import { DiscPageComponent } from './shop/disc-details/disc-page.component';
import { AddressComponent } from './shop/address/address.component';
import { UserListComponent } from './shop/user-list/user-list.component';
import { AuthGuardService } from './security/guards/user-guard/auth-guard.service';
import { AdminGuardService } from './security/guards/admin-guard/admin-guard.service';
import { DiscEditComponent } from './shop/disc-edit/disc-edit.component';
import { CategoryListComponent } from './shop/category-list/category-list.component';

const routes: Routes = [
  { path: 'discs', component: DiscListComponent, data: { animation: 'Disc' }, canActivate: [AuthGuardService] },
  { path: 'register', component: UserFormComponent, data: { animation: 'Register' } },
  { path: 'login', component: LoginComponent, data: { animation: 'Login' } },
  { path: 'main', component: MainPageComponent, data: { animation: 'Main' } },
  { path: 'discs/add', component: DiscFormComponent, data: { animation: 'Add' }, canActivate: [AdminGuardService] },
  { path: 'cart', component: CartComponent, data: { animation: 'Cart' }, canActivate: [AuthGuardService] },
  { path: 'orders', component: OrderListComponent, data: { animation: 'Orders' }, canActivate: [AuthGuardService] },
  { path: 'disc/:id', component: DiscPageComponent, data: { animation: 'Details' }, canActivate: [AuthGuardService] },
  { path: 'profile', component: AddressComponent, data: { animation: 'Profile' }, canActivate: [AuthGuardService] },
  { path: 'user/all', component: UserListComponent, data: { animation: 'UserList' }, canActivate: [AdminGuardService] },
  { path: 'edit/:id', component: DiscEditComponent, data: { animation: 'EditDisc' }, canActivate: [AdminGuardService] },
  { path: 'category', component: CategoryListComponent, data: { animation: 'Category' }, canActivate: [AdminGuardService] },
  { path: '**', redirectTo: 'main' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  declarations: [],
  providers: [
    AuthGuardService,
    AdminGuardService
  ]
})
export class AppRoutingModule { }
