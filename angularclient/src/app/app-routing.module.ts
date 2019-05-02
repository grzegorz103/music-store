import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DiscListComponent } from './disc-list/disc-list.component';
import { UserFormComponent } from './register-form/register-form.component';
const routes: Routes = [
  { path: 'discs', component: DiscListComponent },
  { path: 'register', component: UserFormComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule { }
