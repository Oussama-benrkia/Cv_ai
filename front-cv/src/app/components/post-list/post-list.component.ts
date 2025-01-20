import { Component, OnInit } from '@angular/core';
import {Post} from '../../models/post.model';
import {PostService} from '../../services/post-service';
import {PageResponse} from '../../models/page-response-model';


@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {
  posts: Post[] = [];
  page = 0;
  size = 5;
  totalElements = 0;
  constructor(private postService: PostService) {}

  ngOnInit(): void {
    this.fetchPosts();
  }

  fetchPosts(): void{
    this.postService.findAllPosts(this.page,this.size).subscribe(
      (response:PageResponse<Post>) => {
        this.posts = response.content;
        this.totalElements=response.totalElements
      },
      (error) => {
        console.error('Error fetching posts:', error);
      }
    )
  }

  onPageChange(event: any): void {
    this.page = event.pageIndex;
    this.fetchPosts();
  }
  onPageSizeChange(event:any){
    this.size=event.pageSize;
    this.fetchPosts()
  }
}
