import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { 
  faLayerGroup, 
  faChartLine, 
  faUserGraduate, 
  faBriefcase, 
  faClipboardList, 
  faFileAlt, 
  faPaperPlane, 
  faSearch, 
  faBell, 
  faUser,
  faPlus,
  faEdit,
  faTrash,
  faChevronLeft,
  faChevronRight,
  faEye,
  faDownload,
  faClock,
  faCheckCircle
} from '@fortawesome/free-solid-svg-icons';
import { UserService } from '../../services/user.service';
import { User, UserResponse } from '../../interfaces/user.interface';
import { PostService } from '../../services/post.service';
import { Post } from '../../interfaces/post.interface';
import { CV, CVResponse } from '../../interfaces/cv.interface';
import { MatDialog } from '@angular/material/dialog';
import { AddUserDialogComponent } from '../add-user-dialog/add-user-dialog.component';
import { Application, ApplicationResponse } from '../../interfaces/application.interface';

// Define interface for type safety
interface DashboardStats {
  totalUsers: number;
  totalCvs: number;
  totalPosts: number;
  totalEtudiantUsers: number;
  totalRhUsers: number;
}

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent implements OnInit {
  // Icons
  faLayerGroup = faLayerGroup;
  faChartLine = faChartLine;
  faUserGraduate = faUserGraduate;
  faBriefcase = faBriefcase;
  faClipboardList = faClipboardList;
  faPaperPlane = faPaperPlane;
  faSearch = faSearch;
  faBell = faBell;
  faUser = faUser;
  faPlus = faPlus;
  faEdit = faEdit;
  faTrash = faTrash;
  faChevronLeft = faChevronLeft;
  faChevronRight = faChevronRight;
  faEye = faEye;
  faDownload = faDownload;
  faClock = faClock;
  faCheckCircle = faCheckCircle;
  faFileAlt = faFileAlt;

  stats: DashboardStats = {
    totalUsers: 0,
    totalCvs: 0,
    totalPosts: 0,
    totalEtudiantUsers: 0,
    totalRhUsers: 0
  };
  isLoading = false;
  error: string | null = null;
  activeSection: string = 'dashboard';

  users: User[] = [
    //////////////////////////////////////////// Students
    {
      id: 1,
      nom: "Smith",
      prenom: "John",
      email: "john.smith@example.com",
      role: "STUDENT",
      image: "https://ui-avatars.com/api/?name=John+Smith"
    },
    {
      id: 2,
      nom: "Johnson",
      prenom: "Emma",
      email: "emma.johnson@example.com",
      role: "STUDENT",
      image: "https://ui-avatars.com/api/?name=Emma+Johnson"
    },
    {
      id: 3,
      nom: "Williams",
      prenom: "Michael",
      email: "michael.williams@example.com",
      role: "STUDENT",
      image: "https://ui-avatars.com/api/?name=Michael+Williams"
    },
    {
      id: 4,
      nom: "Brown",
      prenom: "Sarah",
      email: "sarah.brown@example.com",
      role: "STUDENT",
      image: "https://ui-avatars.com/api/?name=Sarah+Brown"
    },
    {
      id: 5,
      nom: "Davis",
      prenom: "James",
      email: "james.davis@example.com",
      role: "STUDENT",
      image: "https://ui-avatars.com/api/?name=James+Davis"
    },
    ///////////////////////////////////////// HR Managers
    {
      id: 6,
      nom: "Anderson",
      prenom: "Robert",
      email: "robert.anderson@company.com",
      role: "HR_MANAGER",
      image: "https://ui-avatars.com/api/?name=Robert+Anderson"
    },
    {
      id: 7,
      nom: "Taylor",
      prenom: "Lisa",
      email: "lisa.taylor@techcorp.com",
      role: "HR_MANAGER",
      image: "https://ui-avatars.com/api/?name=Lisa+Taylor"
    },
    {
      id: 8,
      nom: "Martinez",
      prenom: "Carlos",
      email: "carlos.martinez@hrteam.com",
      role: "HR_MANAGER",
      image: "https://ui-avatars.com/api/?name=Carlos+Martinez"
    },
    {
      id: 9,
      nom: "Wilson",
      prenom: "Emily",
      email: "emily.wilson@recruiter.com",
      role: "HR_MANAGER",
      image: "https://ui-avatars.com/api/?name=Emily+Wilson"
    },
    {
      id: 10,
      nom: "Thompson",
      prenom: "David",
      email: "david.thompson@staffing.com",
      role: "HR_MANAGER",
      image: "https://ui-avatars.com/api/?name=David+Thompson"
    }
  ];

  currentPage = 0;
  pageSize = 10;
  totalPages = 0;
  totalElements = 0;

  posts: Post[] = [
    {
      id: 1,
      title: "Senior Software Engineer",
      description: "We are looking for an experienced software engineer...",
      company: "Tech Corp",
      location: "New York, NY",
      type: "FULLTIME",
      status: "ACTIVE",
      createdAt: new Date().toISOString(),
      hrId: 6,
      applications: 12
    },
    {
      id: 2,
      title: "UX Designer",
      description: "Looking for a creative UX designer...",
      company: "Design Studio",
      location: "San Francisco, CA",
      type: "FULLTIME",
      status: "ACTIVE",
      createdAt: new Date().toISOString(),
      hrId: 7,
      applications: 8
    },
    {
      id: 3,
      title: "Marketing Intern",
      description: "Join our marketing team as an intern...",
      company: "Marketing Pro",
      location: "Remote",
      type: "INTERNSHIP",
      status: "ACTIVE",
      createdAt: new Date().toISOString(),
      hrId: 8,
      applications: 15
    },
    {
      id: 4,
      title: "Part-time Data Analyst",
      description: "Looking for a data analyst for a part-time position...",
      company: "Data Solutions",
      location: "Chicago, IL",
      type: "PARTTIME",
      status: "ACTIVE",
      createdAt: new Date().toISOString(),
      hrId: 9,
      applications: 6
    },
    {
      id: 5,
      title: "Frontend Developer",
      description: "Join our frontend team...",
      company: "Web Solutions",
      location: "Boston, MA",
      type: "FULLTIME",
      status: "CLOSED",
      createdAt: new Date().toISOString(),
      hrId: 10,
      applications: 20
    }
  ];

  cvSearchTerm = '';
  applicationSearchTerm = '';

  cvs: CV[] = [
    {
      id: 1,
      studentId: 1,
      studentName: 'John Smith',
      title: 'Software Engineer',
      education: 'Bachelor in Computer Science',
      experience: '2 years internship',
      skills: ['JavaScript', 'Angular', 'Node.js', 'Python', 'SQL'],
      status: 'ACTIVE',
      createdAt: new Date().toISOString(),
      lastUpdated: new Date().toISOString()
    },
    // Add more sample CVs...
  ];

  applications: Application[] = [
    {
      id: 1,
      studentId: 1,
      studentName: 'John Smith',
      jobId: 1,
      jobTitle: 'Senior Software Engineer',
      company: 'Tech Corp',
      status: 'PENDING',
      appliedDate: new Date().toISOString(),
      cvId: 1
    },
    // Add more sample applications...
  ];

  constructor(
    private http: HttpClient,
    private userService: UserService,
    private dialog: MatDialog,
    private postService: PostService
  ) {
    this.totalElements = this.users.length;
    this.totalPages = Math.ceil(this.totalElements / this.pageSize);
  }

  // Add method to filter users by role
  getUsersByRole(role: string): User[] {
    return this.users.filter(user => user.role === role);
  }

  // Method to get either students or HR managers based on active section
  getFilteredUsers(): User[] {
    const role = this.activeSection === 'students' ? 'STUDENT' : 'HR_MANAGER';
    return this.getUsersByRole(role);
  }

  ngOnInit() {
    this.fetchDashboardStats();
    // Comment out the loadUsers() call when using static data
    // this.loadUsers();
  }

  fetchDashboardStats() {
    this.isLoading = true;
    this.error = null;

    this.http.get<DashboardStats>(`${environment.apiUrl}/api/dashboard/stats`)
      .subscribe({
        next: (data) => {
          this.stats = data;
          this.isLoading = false;
        },
        error: (error: HttpErrorResponse) => {
          this.error = 'Failed to load dashboard statistics';
          this.isLoading = false;
          console.error('Dashboard stats error:', error);
        }
      });
  }

  loadUsers() {
    this.userService.getUsers(this.currentPage, this.pageSize).subscribe({
      next: (response: UserResponse) => {
        this.users = response.content;
        this.totalPages = response.totalPages;
        this.totalElements = response.totalElements;
      },
      error: (error) => {
        console.error('Error loading users:', error);
      }
    });
  }

  changePage(page: number) {
    this.currentPage = page;
    this.loadUsers();
  }

  editUser(user: User) {
    // Implement edit logic
    console.log('Edit user:', user);
  }

  deleteUser(id: number) {
    if (confirm('Are you sure you want to delete this user?')) {
      this.userService.deleteUser(id).subscribe({
        next: () => {
          this.loadUsers();
        },
        error: (error) => {
          console.error('Error deleting user:', error);
        }
      });
    }
  }

  openAddUserDialog(role: 'STUDENT' | 'HR') {
    console.log(`Opening add ${role} dialog`);
    const dialogRef = this.dialog.open(AddUserDialogComponent, {
      width: '400px',
      data: { role },
    });
  
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        console.log('User added successfully:', result);
        // Optionally, refresh the user list or show a success message
      }
    });
  }

  setActiveSection(section: string) {
    this.activeSection = section;
  }

  extractCompany(email: string): string {
    const domain = email.split('@')[1];
    return domain ? domain.split('.')[0] : '';
  }

  getHrJobPostsCount(hrId: number): number {
    return this.posts.filter(post => post.hrId === hrId).length;
  }

  viewHrDetails(user: User) {
    // Implement view details logic
    console.log('View HR details:', user);
  }

  getActivePostsCount(): number {
    return this.posts.filter(post => post.status === 'ACTIVE').length;
  }

  getTotalApplications(): number {
    return this.posts.reduce((sum, post) => sum + post.applications, 0);
  }

  viewPostDetails(post: Post) {
    console.log('View post details:', post);
  }

  editPost(post: Post) {
    console.log('Edit post:', post);
  }

  deletePost(id: number) {
    if (confirm('Are you sure you want to delete this job post?')) {
      this.postService.deletePost(id).subscribe({
        next: () => {
          this.posts = this.posts.filter(post => post.id !== id);
        },
        error: (error) => {
          console.error('Error deleting post:', error);
        }
      });
    }
  }

  openAddPostDialog() {
    console.log('Open add post dialog');
  }

  getInitials(firstName: string, lastName: string): string {
    return (firstName.charAt(0) + lastName.charAt(0)).toUpperCase();
  }

  getActiveCvsCount(): number {
    return this.cvs.filter(cv => cv.status === 'ACTIVE').length;
  }

  getPendingApplicationsCount(): number {
    return this.applications.filter(app => app.status === 'PENDING').length;
  }
}
