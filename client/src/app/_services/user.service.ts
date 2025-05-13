import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';

const API = 'http://localhost:8083/api/user/';

@Injectable({
    providedIn: 'root'
})
export class UserService {

    constructor(private apiService: ApiService) { }

    viewById = (userId: number): Observable<any> => {
        return this.apiService.get(API + userId, { responseType: 'json' });
    }

    update = (userId: number, body: any): Observable<any> => {
        return this.apiService.put(API + "update/" + userId, body, { responseType: 'json' });
    }
}
