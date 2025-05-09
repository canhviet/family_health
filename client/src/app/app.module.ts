import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule, provideAnimations } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { TopbarComponent } from './components/topbar/topbar.component';
import { LayoutComponent } from './components/layout/layout.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { httpInterceptorProviders } from './_helpers/http.interceptor';
import { MedicalHistoryComponent } from './components/doctor/medical-history/medical-history.component';
import { AllergiesComponent } from './components/doctor/allergies/allergies.component';
import { ImmunizationComponent } from './components/doctor/immunization/immunization.component';
import { TestResultComponent } from './components/doctor/test-result/test-result.component';
import { MedicalRecordsComponent } from './components/user/medical-records/medical-records.component';
import { ProfileManagementComponent } from './components/user/profile-management/profile-management.component';
import { HomeComponent } from './components/user/home/home.component';
import { OverviewComponent } from './components/user/overview/overview.component';
import { AddMedicalComponent } from './components/doctor/medical-history/add-medical/add-medical.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatNativeDateModule, MatOptionModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { AddTestComponent } from './components/doctor/test-result/add-test/add-test.component';
import { AddAllergyComponent } from './components/doctor/allergies/add-allergy/add-allergy.component';
import { AddImmunizationComponent } from './components/doctor/immunization/add-immunization/add-immunization.component';
import { UploadComponent } from './components/upload/upload.component';
import { MatCardModule } from '@angular/material/card';
import { MatTabsModule } from '@angular/material/tabs';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { ViewUserRecordComponent } from './components/view-user-record/view-user-record.component';
import { NavComponent } from './components/nav/nav.component';
import { FamilyComponent } from './components/user/family/family.component';
import { ConnectComponent } from './components/user/connect/connect.component';
import { DocumentComponent } from './components/user/document/document.component';
import { AddMemberComponent } from './components/user/family/add-member/add-member.component';

@NgModule({
    declarations: [
        AppComponent,
        LayoutComponent,
        SidebarComponent,
        TopbarComponent,
        LoginComponent,
        RegisterComponent,
        MedicalHistoryComponent,
        AllergiesComponent,
        ImmunizationComponent,
        TestResultComponent,
        MedicalRecordsComponent,
        ProfileManagementComponent,
        HomeComponent,
        OverviewComponent,
        AddMedicalComponent,
        AddTestComponent,
        AddAllergyComponent,
        AddImmunizationComponent,
        UploadComponent,
        ForgotPasswordComponent,
        ChangePasswordComponent,
        ViewUserRecordComponent,
        NavComponent,
        FamilyComponent,
        ConnectComponent,
        DocumentComponent,
        AddMemberComponent,
    ],
    imports: [
        BrowserModule,
        MatDialogModule,
        FormsModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        ReactiveFormsModule,
        ToastrModule.forRoot({
            timeOut: 3000,
            positionClass: 'toast-top-right',
            preventDuplicates: true,
        }),
        MatDatepickerModule,
        MatInputModule,
        MatNativeDateModule,
        MatCardModule,
        MatTabsModule,

    ],
    providers: [
        CookieService,
        provideAnimations(),
        httpInterceptorProviders,
        provideHttpClient(withInterceptorsFromDi()),
    ],
    bootstrap: [AppComponent],
})
export class AppModule { }
