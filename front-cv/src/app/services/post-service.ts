import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from '../models/post.model';
import {environment} from '../../environments/environment';
import {PageResponse} from '../models/page-response-model';


@Injectable({
  providedIn: 'root'
})
export class PostService {

  private apiUrl = `${environment.apiUrl}/posts`;

  constructor(private http: HttpClient) { }

  findAllPosts(page: number = 0, size: number = 10, key:string="", search:string = ""): Observable<PageResponse<Post>>{
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    if(key){
      params = params.set("key", key)
    }
    if(search){
      params = params.set("search", search)
    }

    return this.http.get<PageResponse<Post>>(this.apiUrl, { params });
  }

  findById(id: number):Observable<Post>{
    return this.http.get<Post>(`${this.apiUrl}/id/${id}`);
  }

  findAllPostsList():Observable<Post[]>{
    return this.http.get<Post[]>(`${this.apiUrl}/list`);
  }
  savePost(formData: FormData):Observable<Post>{
    return this.http.post<Post>(this.apiUrl, formData);
  }
  updatePost(formData: FormData, id: number):Observable<Post>{
    return this.http.put<Post>(`${this.apiUrl}/${id}`, formData);
  }
  deletePost(id:number):Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
  findAllMyPosts(page: number = 0, size: number = 10, key:string="", search:string = ""):Observable<PageResponse<Post>>{
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    if(key){
      params=params.set("key", key)
    }
    if(search){
      params=params.set("search", search)
    }
    return this.http.get<PageResponse<Post>>(`${this.apiUrl}/my`, { params });
  }
  findAllByRhPosts(rhId:number,page: number = 0, size: number = 10, key:string="", search:string = ""):Observable<PageResponse<Post>>{
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    if(key){
      params = params.set("key", key)
    }
    if(search){
      params = params.set("search", search)
    }
    return this.http.get<PageResponse<Post>>(`${this.apiUrl}/rh/${rhId}`, { params });
  }
  findAllByRhPostsList(rhId:number):Observable<Post[]>{
    return this.http.get<Post[]>(`${this.apiUrl}/rh/list/${rhId}`);
  }
}
