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

}
