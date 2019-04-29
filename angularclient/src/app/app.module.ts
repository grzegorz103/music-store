import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { DiscListComponent } from './disc-list/disc-list.component';
import { DiscService } from './service/disc.service';
import { RegisterFormComponent } from './register-form/register-form.component';
 
@NgModule({
  declarations: [
    AppComponent,
    DiscListComponent,
    RegisterFormComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [DiscService],
  bootstrap: [AppComponent]
})
export class AppModule { }