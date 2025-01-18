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

    this.http.post('/auth/login', loginData).subscribe(
      (response: any) => {
        alert('Login successful!');
        console.log(response);

        // Stocker le token si fourni par l'API
        if (response.token) {
          localStorage.setItem('authToken', response.token);
        }

        // Rediriger l'utilisateur après la connexion
        this.router.navigate(['/dashboard']);
      },
      (error) => {
        console.error('Login failed:', error);
        alert('Login failed. Please check your credentials.');
      }
    );
  }
}
