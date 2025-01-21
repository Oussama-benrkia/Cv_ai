import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PostuleRequest,PostuleResponse } from '../models/postule.model';
import {environment} from '../../environments/environment';
import {PageResponse} from '../models/page-response-model';


@Injectable({
  providedIn: 'root'
})
export class PostuleService {
  private apiUrl = `${environment.apiUrl}/postules`;

  constructor(private http: HttpClient) { }

  addPostule(request: PostuleRequest,postId:number):Observable<PostuleResponse> {
    return this.http.post<PostuleResponse>(`${this.apiUrl}/${postId}`, request);
  }
  findAllMyPostule(page: number = 0, size: number = 10):Observable<PageResponse<PostuleResponse>> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get<PageResponse<PostuleResponse>>(`${this.apiUrl}/me`, { params });
  }
  findAllMyPostuleList():Observable<PostuleResponse[]> {
    return this.http.get<PostuleResponse[]>(`${this.apiUrl}/meList`);
  }
  checkPostule(postId:number, etudiantId:number):Observable<boolean> {
    const params = new HttpParams()
      .set('postId', postId.toString())
      .set('etudiantId', etudiantId.toString());
    return this.http.get<boolean>(`${this.apiUrl}/check`, { params });
  }

}
