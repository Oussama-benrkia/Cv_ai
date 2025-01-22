import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { AdminComponent } from './layouts/admin/admin.component';
import { UserComponent } from './layouts/user/user.component';
import { AdminDashboardComponent } from './pages/admin-dashboard/admin-dashboard.component';
import { RhDashboardComponent } from './pages/rh-dashboard/rh-dashboard.component';
import { UserDashboardComponent } from './pages/user-dashboard/user-dashboard.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatDividerModule } from '@angular/material/divider';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatDialogModule } from '@angular/material/dialog';
import { MatRadioModule } from '@angular/material/radio';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { PostListComponent } from './components/post-list/post-list.component';
import { PostDetailsComponent } from './components/post-details/post-details.component';
import { CvListComponent } from './components/cv-list/cv-list.component';
import { CvUploadComponent } from './components/cv-upload/cv-upload.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MyApplicationsComponent } from './components/my-applications/my-applications.component';
import { MatTabsModule } from '@angular/material/tabs';
import { MainDashboardComponent } from './components/main-dashboard/main-dashboard.component';
import { StudentDashboardComponent } from './components/student-dashboard/student-dashboard.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { AddUserDialogComponent } from './pages/add-user-dialog/add-user-dialog.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    DashboardComponent,
    AdminComponent,
    UserComponent,
    AdminDashboardComponent,
    RhDashboardComponent,
    UserDashboardComponent,
    PostListComponent,
    PostDetailsComponent,
    CvListComponent,
    CvUploadComponent,
    MyApplicationsComponent,
    MainDashboardComponent,
    StudentDashboardComponent,
    AddUserDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    RouterModule,
    HttpClientModule,
    FontAwesomeModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
    MatListModule,
    MatDialogModule,
    MatRadioModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatSnackBarModule,
    HttpClientModule,
    MatPaginatorModule,
    MatTabsModule,
    MatMenuModule,
    MatDividerModule
  ],
  providers: [
    provideHttpClient(withInterceptorsFromDi()),
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
