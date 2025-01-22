import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import {CvService} from '../../services/cv-service';
import {CvResponse} from '../../models/cv.model';

@Component({
  selector: 'app-cv-upload',
  templateUrl: './cv-upload.component.html',
  styleUrls: ['./cv-upload.component.css']
})
export class CvUploadComponent {
  selectedFile: File | null = null;
  titre: string = '';

  constructor(private cvService: CvService,
              private snackBar: MatSnackBar) {}

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0] as File;
  }

  uploadFile(): void {
    if (!this.selectedFile || !this.titre.trim()) {
      this.snackBar.open('Selectionner un fichier et ajouter un titre!', 'Fermer', {
        duration: 3000,
      });
      return;
    }

    const token = localStorage.getItem('authToken'); // Replace with your token retrieval logic
    if (!token) {
      this.snackBar.open('Authentication token is missing!', 'Fermer', {
        duration: 3000,
      });
      return;
    }

    const formData = new FormData();
    formData.append('file', this.selectedFile);
    formData.append('titre', this.titre);

    this.cvService.uploadCv(formData, token).subscribe(
      (response: CvResponse) => {
        this.snackBar.open('Cv a été ajoute avec succès!', 'Fermer', {
          duration: 3000,
        });
        this.selectedFile=null;
        this.titre="";
      },
      (error) => {
        this.snackBar.open('Erreur lors du chargement!', 'Fermer', {
          duration: 3000,
        });
        console.error('Error uploading file', error);
      }
    );
  }
}
