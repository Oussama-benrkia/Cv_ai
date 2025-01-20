import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-dashboard',
  standalone: false,
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  role: string = 'user'; // Exemple : récupéré via un service ou une API
  stats: any = {};

  constructor(private http: HttpClient) {}


  ngOnInit() {
    this.fetchDashboardStats();
  }

  fetchDashboardStats() {
    this.http.get('/api/dashboard/stats').subscribe(data => {
      this.stats = data;
    });
  }

  isAdmin() {
    return this.role === 'admin';
  }
}
