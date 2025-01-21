import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from '../../environments/environment';
import {UserResponse} from '../models/user-model';
import {PageResponse} from '../models/page-response-model';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = `${environment.apiUrl}/user`;
  constructor(private http: HttpClient) { }

  findById(id: number): Observable<UserResponse> {
    return this.http.get<UserResponse>(`${this.apiUrl}/${id}`);
  }
  findAll(page: number = 0, size: number = 10, search: string = '', role: string = ''): Observable<PageResponse<UserResponse>> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString())
    if(search){
      params = params.set('search', search)
    }
    if(role){
      params = params.set('role', role)
    }

    return this.http.get<PageResponse<UserResponse>>(this.apiUrl, {params});
  }
  findAllUsers(search: string = '', role: string = ''): Observable<UserResponse[]> {
    let params = new HttpParams()
    if(search){
      params = params.set('search', search)
    }
    if(role){
      params = params.set('role', role)
    }

    return this.http.get<UserResponse[]>(`${this.apiUrl}/list`,{params});
  }
  save(formData: FormData): Observable<UserResponse>{
    return this.http.post<UserResponse>(this.apiUrl, formData);
  }
  update(formData:FormData, id: number):Observable<UserResponse>{
    return this.http.put<UserResponse>(`${this.apiUrl}/${id}`, formData);
  }
  delete(id:number):Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
