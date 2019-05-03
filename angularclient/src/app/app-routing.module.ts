import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DiscListComponent } from './disc-list/disc-list.component';
import { UserFormComponent } from './register-form/register-form.component';
import { LoginComponent } from './login/login.component';
const routes: Routes = [
  { path: 'discs', component: DiscListComponent },
  { path: 'register', component: UserFormComponent },
  { path: 'login', component: LoginComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule { }
