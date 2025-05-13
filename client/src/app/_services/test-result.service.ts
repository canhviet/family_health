import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';

const API = 'http://localhost:8083/api/test-results/';

@Injectable({
    providedIn: 'root'
})
export class TestResultService {
    constructor(private apiService: ApiService) { }

    add = (body: any): Observable<any> => {
        return this.apiService.post(API, body, {});
    }

    viewByUser = (userId: number) : Observable<any> => {
        return this.apiService.get(API + "user/" + userId, { responseType: 'json' });
    }
}
