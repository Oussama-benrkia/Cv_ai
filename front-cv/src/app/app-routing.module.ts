import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { AdminComponent } from './layouts/admin/admin.component';
import { UserComponent } from './layouts/user/user.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { AdminDashboardComponent } from './pages/admin-dashboard/admin-dashboard.component';
import { RhDashboardComponent } from './pages/rh-dashboard/rh-dashboard.component';
import { UserDashboardComponent } from './pages/user-dashboard/user-dashboard.component';
import { MainDashboardComponent } from './components/main-dashboard/main-dashboard.component';
import { PostDetailsComponent } from './components/post-details/post-details.component';
import { StudentDashboardComponent } from './components/student-dashboard/student-dashboard.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'admin-dashboard', component: AdminDashboardComponent },
  { path: 'rh-dashboard', component: RhDashboardComponent },
  { path: 'user-dashboard', component: UserDashboardComponent },
  { path: '', component: MainDashboardComponent },
  { path: 'post/:id', component: PostDetailsComponent },
  { path: 'student-dashboard',component: StudentDashboardComponent,},
  {
    path: 'admin',
    component: AdminComponent,
    children: [
      { path: 'dashboard', component: DashboardComponent },
    ],
  },
  {
    path: 'user',
    component: UserComponent,
    children: [
      { path: 'dashboard', component: DashboardComponent },
    ],
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
