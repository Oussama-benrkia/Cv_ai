import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainDashboardComponent } from './components/main-dashboard/main-dashboard.component';
import { PostDetailsComponent } from './components/post-details/post-details.component';
import { StudentDashboardComponent } from './components/student-dashboard/student-dashboard.component';

const routes: Routes = [
  { path: '', component: MainDashboardComponent },
  { path: 'post/:id', component: PostDetailsComponent },
  {
    path: 'student-dashboard',
    component: StudentDashboardComponent,
    // Add auth guard if needed
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
