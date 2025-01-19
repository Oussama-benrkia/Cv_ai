import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: false,
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  role: string = ''; // Rôle de l'utilisateur (user, rh, admin)
  userData: any = {}; // Données spécifiques à l'utilisateur
  offers: any[] = []; // Pour user
  applications: any[] = []; // Pour user ou RH
  posts: any[] = []; // Pour RH
  users: any[] = []; // Pour admin
  stats: any = {}; // Pour admin

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    // Appel au backend pour récupérer le rôle de l'utilisateur
    this.http.get('/api/auth/me').subscribe((response: any) => {
      this.role = response.role; // Récupérer le rôle depuis la réponse
      this.userData = response;

      // Charger les données en fonction du rôle
      if (this.role === 'user') {
        this.loadUserDashboard();
      } else if (this.role === 'rh') {
        this.loadRHDashboard();
      } else if (this.role === 'admin') {
        this.loadAdminDashboard();
      }
    },
    (error) => {
      console.error('Erreur lors de la récupération du rôle de l\'utilisateur', error);
      this.router.navigate(['/login']);
    }
  );
  }

  loadUserDashboard(): void {
    // Charger les offres disponibles
    this.http.get('/api/posts/list').subscribe(
      (response: any) => {
        this.offers = response;
      },
      (error) => {
        console.error('Erreur lors du chargement des offres', error);
      }
    );

    // Charger les candidatures de l'utilisateur
    this.http.get('/api/postules/meList').subscribe(
      (response: any) => {
        this.applications = response;
      },
      (error) => {
        console.error('Erreur lors du chargement des candidatures', error);
      }
    );
  }

  loadRHDashboard(): void {
    // Charger les annonces créées par le RH
    this.http.get('/api/posts/my').subscribe(
      (response: any) => {
        this.posts = response;
      },
      (error) => {
        console.error('Erreur lors du chargement des annonces', error);
      }
    );

    // Charger les candidatures reçues pour les annonces du RH
    this.http.get('/api/postules/postList').subscribe(
      (response: any) => {
        this.applications = response;
      },
      (error) => {
        console.error('Erreur lors du chargement des candidatures', error);
      }
    );
  }

  loadAdminDashboard(): void {
    // Charger la liste des utilisateurs
    this.http.get('/api/user/list').subscribe(
      (response: any) => {
        this.users = response;
      },
      (error) => {
        console.error('Erreur lors du chargement des utilisateurs', error);
      }
    );

    // Charger les statistiques (si nécessaire)
    this.http.get('/api/dashboard/stats').subscribe(
      (response: any) => {
        this.stats = response;
      },
      (error) => {
        console.error('Erreur lors du chargement des statistiques', error);
      }
    );
  }


  // Actions spécifiques par rôle
   // Postuler à une offre (user)
   applyToOffer(offerId: number): void {
    const requestBody = { etudiantId: this.userData.id };
    this.http.post(`/api/postules/${offerId}`, requestBody).subscribe(
      () => alert('Candidature envoyée avec succès !'),
      (error) => alert('Erreur lors de la candidature.')
    );
  }

  // Supprimer une annonce (RH)
  deletePost(postId: number): void {
    this.http.delete(`/api/posts/${postId}`).subscribe(
      () => alert('Annonce supprimée avec succès !'),
      (error) => alert('Erreur lors de la suppression de l\'annonce.')
    );
  }

  // Activer/Désactiver un utilisateur (admin)
  toggleUserStatus(userId: number, isActive: boolean): void {
    const action = isActive ? 'deactivate' : 'activate';
    this.http.post(`/api/user/${action}/${userId}`, {}).subscribe(
      () => alert(`Utilisateur ${action} avec succès !`),
      (error) => alert(`Erreur lors de l'opération sur l'utilisateur.`)
    );
  }
}