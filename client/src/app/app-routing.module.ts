import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './components/layout/layout.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/home/profile/profile.component';
import { MedicalHistoryComponent } from './components/home/medical-history/medical-history.component';
import { AllergiesComponent } from './components/home/allergies/allergies.component';
import { ImmunizationComponent } from './components/home/immunization/immunization.component';
import { MedicationComponent } from './components/home/medication/medication.component';
import { TestResultComponent } from './components/home/test-result/test-result.component';
import { DocumentComponent } from './components/home/document/document.component';

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
        {
            path: 'profile',
            component: ProfileComponent
        },
        {
            path: 'medical-history',
            component: MedicalHistoryComponent
        },
        {
            path: 'allergies',
            component: AllergiesComponent
        },
        {
            path: 'immunization',
            component: ImmunizationComponent
        },
        {
            path: 'medication',
            component: MedicationComponent
        },
        {
            path: 'test-result',
            component: TestResultComponent
        },
        {
            path: 'document',
            component: DocumentComponent
        }
      ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
