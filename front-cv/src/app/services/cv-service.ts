import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cv,CvResponse } from '../models/cv.model';
import {environment} from '../../environments/environment';
import {PageResponse} from '../models/page-response-model';


@Injectable({
  providedIn: 'root'
})
export class CvService {

  private apiUrl = `${environment.apiUrl}/cvs`;

  constructor(private http: HttpClient) { }

  uploadCv(formData: FormData): Observable<CvResponse> {
    return this.http.post<CvResponse>(this.apiUrl, formData);
  }

  getAllCvsOfUser(page: number = 0, size: number = 10, titre:string = ""):Observable<PageResponse<CvResponse>>{
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    if(titre){
      params=params.set('titre', titre);
    }
    return this.http.get<PageResponse<CvResponse>>(this.apiUrl, { params });
  }

  getAllCvsOfUserWithoutPagination(titre:string = ""):Observable<CvResponse[]>{
    let params = new HttpParams()
    if(titre){
      params=params.set('titre', titre);
    }
    return this.http.get<CvResponse[]>(`${this.apiUrl}/all`, { params });
  }

}
