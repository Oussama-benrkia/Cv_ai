import { Component, OnInit } from '@angular/core';
import {PostuleResponse} from '../../models/postule.model';
import {PostuleService} from '../../services/postule-service';
import {PageResponse} from '../../models/page-response-model';




@Component({
  selector: 'app-my-applications',
  templateUrl: './my-applications.component.html',
  styleUrls: ['./my-applications.component.css']
})
export class MyApplicationsComponent implements OnInit {
  applications: PostuleResponse[]=[];
  page = 0;
  size = 5;
  totalElements = 0;
  constructor(private postuleService: PostuleService){}
  ngOnInit(): void {
    this.fetchApplications();
  }
  fetchApplications(){
    this.postuleService.findAllMyPostule(this.page,this.size).subscribe((response:PageResponse<PostuleResponse>)=>{
      this.applications=response.content;
      this.totalElements=response.totalElements;
    });
  }
  onPageChange(event: any): void {
    this.page = event.pageIndex;
    this.fetchApplications();
  }
  onPageSizeChange(event:any){
    this.size=event.pageSize;
    this.fetchApplications()
  }

}
