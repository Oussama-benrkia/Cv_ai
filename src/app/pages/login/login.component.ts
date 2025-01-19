import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(private http: HttpClient, private router: Router) {}

  onLogin() {
    const loginData = {
      email: this.email,
      password: this.password,
    };

    this.http.post('http://localhost:8080/auth/login', loginData).subscribe(
      (response: any) => {
        console.log('Login Response:', response);

        if (response.token) {
          alert('Login successful!');
          localStorage.setItem('authToken', response.token);

          // Adjust this to match the backend structure
          if (response.user?.role) {
            console.log('User Role:', response.user.role);
            localStorage.setItem('userRole', response.user.role);

            // Redirect based on user role
            if (response.user.role === 'admin') {
              this.router.navigate(['/admin-dashboard']);
            } else if (response.user.role === 'rh') {
              this.router.navigate(['/rh-dashboard']);
            } else {
              this.router.navigate(['/user-dashboard']);
            }
          } else {
            console.error('User role not found in response.');
            alert('User role not found. Please contact support.');
          }
        } else {
          alert('Login failed: No token received.');
        }
      },
      (error) => {
        console.error('Login failed:', error);
        alert('Login failed. Please check your credentials.');
      }
    );
  }
}
