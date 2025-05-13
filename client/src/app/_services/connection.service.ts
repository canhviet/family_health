import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';

const API = 'http://localhost:8083/api/connection/';

@Injectable({
    providedIn: 'root'
})
export class ConnectionService {
    constructor(private apiService: ApiService) { }

    viewDoctors = (userId: number): Observable<any> => {
        return this.apiService.get(API + "doctors/" + userId, { responseType: 'json' });
    }

    searchPatients = (doctorId: number, search: string): Observable<any> => {
        return this.apiService.get(API + "patients/" + doctorId + "/" + search, { responseType: 'json' });
    }

    searchDoctors = (userId: number, search: string): Observable<any> => {
        return this.apiService.get(API + userId + "/" + search, { responseType: 'json' });
    }

    add = (body: any): Observable<any> => {
        return this.apiService.post(API, body, {});
    };

}
