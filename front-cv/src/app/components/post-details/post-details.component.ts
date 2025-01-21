import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { MatDialog } from '@angular/material/dialog';
import { CvListComponent } from '../cv-list/cv-list.component';

import { MatSnackBar } from '@angular/material/snack-bar';
import {Post} from '../../models/post.model';
import {PostService} from '../../services/post-service';
import {PostuleService} from '../../services/postule-service';

@Component({
  selector: 'app-post-details',
  templateUrl: './post-details.component.html',
  styleUrls: ['./post-details.component.css']
})
export class PostDetailsComponent implements OnInit {

  postId:number =0;
  post?:Post;
  etudiantId = 1 //TODO : to change with user connected ID
  hasPostuled:boolean=false;

  constructor(
    private route: ActivatedRoute,
    private postService: PostService,
    private dialog: MatDialog,
    private postuleService : PostuleService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.postId = +params['id'];
      this.fetchPost();
    });
  }
  fetchPost(){
    this.postService.findById(this.postId).subscribe(data=>{
      this.post=data;
      this.checkPostule();
    });
  }
  checkPostule(){
    this.postuleService.checkPostule(this.postId,this.etudiantId).subscribe(data=>{
      this.hasPostuled=data;
    });
  }

  openCvListDialog(): void {
    const dialogRef = this.dialog.open(CvListComponent, {
      width: '600px',
      data: {postId: this.postId},
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.postuleService.addPostule({cvId:result, postId:this.postId},this.postId).subscribe(data=>{
          this.snackBar.open('Votre candidature a été soumise avec succès!', 'Fermer', {
            duration: 3000,
          });
          this.hasPostuled=true;
        },err=>{
          this.snackBar.open('Erreur lors de la candidature!', 'Fermer', {
            duration: 3000,
          });
        });
      }
    });
  }
}
