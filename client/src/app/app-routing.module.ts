import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './components/layout/layout.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  {
      path: '',
      component: LoginComponent,
  },
  {
      path: 'login',
      component: LoginComponent,
  },
  {
      path: 'sign_up',
      component: RegisterComponent,
  },
  {
      path: 'home',
      component: LayoutComponent,
      children: [

      ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
