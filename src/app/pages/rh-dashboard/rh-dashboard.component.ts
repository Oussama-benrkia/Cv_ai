import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-rh-dashboard',
  templateUrl: './rh-dashboard.component.html',
  styleUrls: ['./rh-dashboard.component.css'],
})
export class RhDashboardComponent implements OnInit {
  rhData: any = {}; // RH profile details
  posts: any[] = []; // Job posts created by RH
  applications: any[] = []; // Applications for selected post
  showModal: boolean = false; // RH Profile modal visibility
  showAddPostModal: boolean = false; // Add Post modal visibility
  newPost: any = { title: '', description: '' }; // New post data

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.loadRHData();
    this.loadPosts();
  }

  // Load RH profile details
  loadRHData(): void {
    this.http.get('/api/auth/me').subscribe(
      (response: any) => {
        this.rhData = response;
      },
      (error) => {
        console.error('Error loading RH data:', error);
      }
    );
  }

  // Load RH's job posts
  loadPosts(): void {
    this.http.get('/api/posts/my').subscribe(
      (response: any) => {
        this.posts = response;
      },
      (error) => {
        console.error('Error loading posts:', error);
      }
    );
  }

  // View applications for a job post
  viewApplications(postId: number): void {
    this.http.get(`/api/postules/postList/${postId}`).subscribe(
      (response: any) => {
        this.applications = response;
      },
      (error) => {
        console.error('Error loading applications:', error);
      }
    );
  }

  // Analyze CV with AI
  analyzeCV(cvId: number): void {
    this.http.get(`/api/cvs/${cvId}`).subscribe(
      (response: any) => {
        alert('CV analysis complete. Results: ' + JSON.stringify(response));
      },
      (error) => {
        console.error('Error analyzing CV:', error);
      }
    );
  }

  // Add a new job post
  addPost(): void {
    this.http.post('/api/posts', this.newPost).subscribe(
      () => {
        alert('Job posted successfully!');
        this.toggleAddPostModal();
        this.loadPosts();
      },
      (error) => {
        console.error('Error adding post:', error);
      }
    );
  }

  // Delete a job post
  deletePost(postId: number): void {
    this.http.delete(`/api/posts/${postId}`).subscribe(
      () => {
        alert('Job post deleted successfully!');
        this.loadPosts();
      },
      (error) => {
        console.error('Error deleting post:', error);
      }
    );
  }

  // Toggle modal visibility
  toggleModal(): void {
    this.showModal = !this.showModal;
  }

  toggleAddPostModal(): void {
    this.showAddPostModal = !this.showAddPostModal;
  }

  // Logout function
  logout(): void {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}
