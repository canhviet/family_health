import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './components/layout/layout.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { MedicalHistoryComponent } from './components/doctor/medical-history/medical-history.component';
import { AllergiesComponent } from './components/doctor/allergies/allergies.component';
import { ImmunizationComponent } from './components/doctor/immunization/immunization.component';
import { TestResultComponent } from './components/doctor/test-result/test-result.component';
import { ProfileManagementComponent } from './components/user/profile-management/profile-management.component';
import { MedicalRecordsComponent } from './components/user/medical-records/medical-records.component';
import { HomeComponent } from './components/user/home/home.component';
import { OverviewComponent } from './components/user/overview/overview.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { FamilyComponent } from './components/user/family/family.component';
import { ConnectComponent } from './components/user/connect/connect.component';

const routes: Routes = [
    {
        path: '',
        component: LoginComponent,
    },
    {
        path: 'forgot-password',
        component: ForgotPasswordComponent
    },
    {
        path: 'change-password',
        component: ChangePasswordComponent
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
        component: HomeComponent,
        children: [
            {
                path: '',
                component: OverviewComponent
            },
            {
                path: 'profile',
                component: ProfileManagementComponent
            },
            {
                path: 'medical-record',
                component: MedicalRecordsComponent
            },
            {
                path: 'family',
                component: FamilyComponent
            },
            {
                path: 'connect',
                component: ConnectComponent
            }
        ]
    },
    {
        path: 'doctor',
        component: LayoutComponent,
        children: [
            {
                path: 'medical',
                component: MedicalHistoryComponent
            },
            {
                path: 'allergy',
                component: AllergiesComponent
            },
            {
                path: 'immunization',
                component: ImmunizationComponent
            },
            {
                path: 'test',
                component: TestResultComponent
            },
        ],
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
