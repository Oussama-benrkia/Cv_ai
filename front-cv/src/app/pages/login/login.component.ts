import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { jwtDecode } from 'jwt-decode';

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

    this.http.post('http://localhost:8081/auth/login', loginData).subscribe(
      (response: any) => {
        console.log('Login Response:', response);

        if (response.token) {
          console.log('Token:', response.token); // Log the token to the console
          alert('Login successful!');
          localStorage.setItem('authToken', response.token);

          const decodedToken: any = jwtDecode(response.token);
          console.log('Decoded Token:', decodedToken);

          // Extract the role from the decoded token
          const roles = decodedToken.role; // Assuming 'role' is an array
          const primaryRole = roles[0]; // Use the first role if multiple roles exist

          if (primaryRole) {
            console.log('User Role:', primaryRole);
            localStorage.setItem('userRole', primaryRole);

            // Redirect based on user role
            if (primaryRole === 'ADMIN') {
              this.router.navigate(['/admin-dashboard']);
            } else if (primaryRole === 'RH') {
              this.router.navigate(['/rh-dashboard']);
            } else {
              this.router.navigate(['/student-dashboard']);
            }
          } else {
            console.error('User role not found in token.');
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
