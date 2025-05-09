import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';

const API = 'http://localhost:8083/api/document/';

@Injectable({
    providedIn: 'root'
})
export class DocumentService {

    constructor(private apiService: ApiService) { }

    addDocument = (body: any): Observable<any> => {
        return this.apiService.post(API, body, {});
    };

    getByUser = (userId: any): Observable<any> => {
        return this.apiService.get(API + "user/" + userId, { responseType: 'json' });
    }
}
