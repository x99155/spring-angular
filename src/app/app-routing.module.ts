import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { LoginComponent } from './login/login.component';
import { StudentComponent } from './student/student.component';
import { PaymentComponent } from './payment/payment.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoadStudentComponent } from './load-student/load-student.component';
import { LoadPaymentComponent } from './load-payment/load-payment.component';
import { AdminTemplateComponent } from './admin-template/admin-template.component';
import { StudentDetailsComponent } from './student-details/student-details.component';

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'admin', component: AdminTemplateComponent, children: [
    {path: 'home', component: HomeComponent},
    {path: 'profile', component: ProfileComponent},
    {path: 'student', component: StudentComponent},
    {path: 'payment', component: PaymentComponent},
    {path: 'dashboard', component: DashboardComponent},
    {path: 'loadStudent', component: LoadStudentComponent},
    {path: 'loadPayment', component: LoadPaymentComponent},
    {path: 'student-details/:code', component: StudentDetailsComponent},
  ]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
