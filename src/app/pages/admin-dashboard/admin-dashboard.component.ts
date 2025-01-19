import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css'],
})
export class AdminDashboardComponent implements OnInit {
  stats: any = {}; // Admin statistics
  adminData: any = {}; // Admin profile details
  users: any[] = []; // List of users
  posts: any[] = []; // List of posts
  filteredPosts: any[] = []; // Filtered posts for search
  searchTerm: string = ''; // Search term for filtering posts
  showModal: boolean = false; // For modal visibility

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.loadStatistics();
    this.loadAdminData();
    this.loadUsers();
    this.loadPosts();
  }

  // Load statistics for the admin dashboard
  loadStatistics(): void {
    this.http.get('/api/dashboard/stats').subscribe(
      (response: any) => {
        this.stats = response;
      },
      (error) => {
        console.error('Error loading statistics:', error);
      }
    );
  }

  // Load admin profile details
  loadAdminData(): void {
    this.http.get('/api/auth/me').subscribe(
      (response: any) => {
        this.adminData = response;
      },
      (error) => {
        console.error('Error loading admin data:', error);
      }
    );
  }

  // Load list of users
  loadUsers(): void {
    this.http.get('/api/user/list').subscribe(
      (response: any) => {
        this.users = response;
      },
      (error) => {
        console.error('Error loading users:', error);
      }
    );
  }

  // Load all posts
  loadPosts(): void {
    this.http.get('/api/posts/list').subscribe(
      (response: any) => {
        this.posts = response;
        this.filteredPosts = [...this.posts];
      },
      (error) => {
        console.error('Error loading posts:', error);
      }
    );
  }

  // Filter posts based on search term
  searchPosts(): void {
    this.filteredPosts = this.posts.filter((post) =>
      post.title.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      post.company.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      post.tags.some((tag: string) =>
        tag.toLowerCase().includes(this.searchTerm.toLowerCase())
      )
    );
  }

  // Toggle modal visibility
  toggleModal(): void {
    this.showModal = !this.showModal;
  }

  // Update admin account details (future use)
  updateAccount(): void {
    this.http.put('/api/auth/update', this.adminData).subscribe(
      () => {
        alert('Account updated successfully!');
        this.toggleModal();
      },
      (error) => {
        console.error('Error updating account:', error);
        alert('Failed to update account.');
      }
    );
  }

  // Toggle user activation/deactivation
  toggleUserStatus(userId: number, isActive: boolean): void {
    const action = isActive ? 'deactivate' : 'activate';
    this.http.post(`/api/user/${action}/${userId}`, {}).subscribe(
      () => {
        alert(`User ${action}d successfully!`);
        this.loadUsers();
      },
      (error) => {
        console.error(`Error ${action}ing user:`, error);
        alert(`Failed to ${action} user.`);
      }
    );
  }

  // Logout function
  logout(): void {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}
