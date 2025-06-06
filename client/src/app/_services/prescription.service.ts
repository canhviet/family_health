import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';

const API = 'http://localhost:8083/api/prescription/';

@Injectable({
    providedIn: 'root'
})
export class PrescriptionService {

    constructor(private apiService: ApiService) { }

    add = (body: any): Observable<any> => {
        return this.apiService.post(API, body, {});
    };

    viewByUser = (userId: number) : Observable<any> => {
        return this.apiService.get(API + "user/" + userId, { responseType: 'json' });
    }

    reminder = (userId: number) : Observable<any> => {
        return this.apiService.get(API + "reminder/" + userId, { responseType: 'json' });
    }

}
