import { Component, Inject, OnInit } from '@angular/core';

import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

import { MatSnackBar } from '@angular/material/snack-bar';
import {CvResponse} from '../../models/cv.model';
import {PageResponse} from '../../models/page-response-model';
import {CvService} from '../../services/cv-service';

@Component({
  selector: 'app-cv-list',
  templateUrl: './cv-list.component.html',
  styleUrls: ['./cv-list.component.css']
})
export class CvListComponent implements OnInit {
  cvs: CvResponse[] = [];
  selectedCvId:number=0;
  page = 0;
  size = 5;
  totalElements = 0;
  constructor(
    private cvService: CvService,
    private dialogRef: MatDialogRef<CvListComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { postId: number },
    private snackBar: MatSnackBar
  ) {}
  ngOnInit(): void {
    this.fetchCvs();
  }

  fetchCvs(): void{
    this.cvService.getAllCvsOfUser(this.page,this.size).subscribe(
      (response:PageResponse<CvResponse>) => {
        this.cvs = response.content;
        this.totalElements=response.totalElements
      },
      (error) => {
        console.error('Error fetching posts:', error);
      }
    )
  }
  onSelectCv(cvId: number) {
    this.selectedCvId = cvId;
  }

  onApply() {
    if (this.selectedCvId) {
      this.dialogRef.close(this.selectedCvId);
    } else {
      this.snackBar.open('Selectionner un cv!', 'Fermer', {
        duration: 3000,
      });
    }
  }
  onClose(){
    this.dialogRef.close();
  }
  onPageChange(event: any): void {
    this.page = event.pageIndex;
    this.fetchCvs();
  }
  onPageSizeChange(event:any){
    this.size=event.pageSize;
    this.fetchCvs()
  }

}
