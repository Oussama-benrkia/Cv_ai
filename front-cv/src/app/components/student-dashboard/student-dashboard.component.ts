import { Component, OnInit } from '@angular/core';
import { Post } from '../../models/post.model';
import { CvResponse } from '../../models/cv.model';
import { PostuleResponse } from '../../models/postule.model'; // Import from the model file
import { PostService } from '../../services/post-service';
import { PostuleService } from '../../services/postule-service';
import { CvService } from '../../services/cv-service';
import { MatDialog } from '@angular/material/dialog';
import { CvUploadComponent } from '../cv-upload/cv-upload.component';

@Component({
  selector: 'app-student-dashboard',
  templateUrl: './student-dashboard.component.html',
  styleUrls: ['./student-dashboard.component.css']
})
export class StudentDashboardComponent implements OnInit {
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

  myCvs: CvResponse[] = [
    {
      id: 1,
      titre: 'Software Developer CV',
      path: '/path/to/cv1',
      createdAt: new Date('2024-01-01')
    },
    {
      id: 2,
      titre: 'Web Developer CV',
      path: '/path/to/cv2',
      createdAt: new Date('2024-01-10')
    }
  ];

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
      (cvs: CvResponse[]) => this.myCvs = cvs.map(cv => ({
        ...cv,
        createdAt: new Date(cv.createdAt)
      })),
      error => console.error('Error loading CVs:', error)
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