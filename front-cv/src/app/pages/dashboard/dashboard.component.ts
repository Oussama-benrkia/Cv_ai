import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  standalone: false,
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  role: string = 'user'; // Exemple : récupéré via un service ou une API

  constructor() {}

  isAdmin() {
    return this.role === 'admin';
  }
}
