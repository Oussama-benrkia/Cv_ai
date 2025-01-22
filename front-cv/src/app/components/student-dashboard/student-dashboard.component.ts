import { Component, OnInit } from '@angular/core';
import { Post } from '../../models/post.model';
import { CvResponse } from '../../models/cv.model';
import { PostuleResponse } from '../../models/postule.model'; // Import from the model file
import { PostService } from '../../services/post-service';
import { PostuleService } from '../../services/postule-service';
import { CvService } from '../../services/cv-service';
import { MatDialog } from '@angular/material/dialog';
import { CvUploadComponent } from '../cv-upload/cv-upload.component';
import { MatSnackBar } from '@angular/material/snack-bar'; // Add this import

import { MatCardModule } from '@angular/material/card'; // Import MatCardModule
import { MatIconModule } from '@angular/material/icon'; // Import MatIconModule if you're using mat-icon
import { MatButtonModule } from '@angular/material/button'; // Import MatButtonModule if you're using mat-button
import { MatMenuModule } from '@angular/material/menu'; // Import MatMenuModule if you're using mat-menu
import { MatListModule } from '@angular/material/list'; // Import MatListModule if you're using mat-list
import { MatDialogModule } from '@angular/material/dialog'; // Import MatDialogModule if you're using MatDialog
@Component({
  selector: 'app-student-dashboard',
  templateUrl: './student-dashboard.component.html',
  styleUrls: ['./student-dashboard.component.css']
})
export class StudentDashboardComponent implements OnInit {
  myCvs: CvResponse[] = [];

  myApplications: PostuleResponse[] = [
    {
      postId: 1,
      description: 'Frontend Developer Position',
      message: 'Application under review',
      pourcentage: 75,
      creatAt: '2024-01-15',
      etudiantId: 1,
      status: 'Pending'
    },
    {
      postId: 2,
      description: 'Full Stack Developer',
      message: 'Interview scheduled',
      pourcentage: 90,
      creatAt: '2024-01-10',
      etudiantId: 1,
      status: 'Accepted'
    },
    {
      postId: 3,
      description: 'UI/UX Designer',
      message: 'Not selected',
      pourcentage: 30,
      creatAt: '2024-01-05',
      etudiantId: 1,
      status: 'Rejected'
    }
  ];

  // myCvs: CvResponse[] = [
  //   {
  //     id: 1,
  //     titre: 'Software Developer CV',
  //     path: '/path/to/cv1',
  //     createdAt: new Date('2024-01-01')
  //   },
  //   {
  //     id: 2,
  //     titre: 'Web Developer CV',
  //     path: '/path/to/cv2',
  //     createdAt: new Date('2024-01-10')
  //   }
  // ];

  recentPosts = [
    {
      id: 1,
      titre: 'Senior Frontend Developer',
      email: 'company1@example.com',
      description: 'Looking for experienced frontend developer'
    },
    {
      id: 2,
      titre: 'Backend Developer',
      email: 'company2@example.com',
      description: 'Java Spring Boot developer needed'
    },
    {
      id: 3,
      titre: 'DevOps Engineer',
      email: 'company3@example.com',
      description: 'AWS and Docker experience required'
    }
  ];
  
  constructor(
    private postService: PostService,
    private postuleService: PostuleService,
    private cvService: CvService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadDashboardData();
  }

  loadDashboardData(): void {
    // Load recent applications
    this.postuleService.findAllMyPostuleList().subscribe(
      (applications: PostuleResponse[]) => this.myApplications = applications,
      error => console.error('Error loading applications:', error)
    );

    // Load CVs
    this.cvService.getAllCvsOfUserWithoutPagination().subscribe(
      (response: CvResponse[]) => {
        this.myCvs = response;
      },
      (error) => {
        console.error('Error loading CVs:', error);
        if (error.status === 403) {
          this.snackBar.open('Access denied. Please log in again.', 'Close', { duration: 3000 });
        } else {
          this.snackBar.open('Failed to load CVs. Please try again.', 'Close', { duration: 3000 });
        }
      }
    );

    // Load recent job posts
    this.postService.findAllPosts(0, 5).subscribe(
      response => this.recentPosts = response.content,
      error => console.error('Error loading posts:', error)
    );
  }

  getStatusColor(status: string): string {
    switch(status.toLowerCase()) {
      case 'accepted': return 'green';
      case 'rejected': return 'red';
      default: return 'orange';
    }
  }

  openCvUploadDialog(): void {
    const dialogRef = this.dialog.open(CvUploadComponent, {
      width: '500px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.loadDashboardData();
      }
    });
  }
}