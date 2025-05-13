import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';

const API = 'http://localhost:8083/api/allergy/';

@Injectable({
    providedIn: 'root'
})
export class AllergyService {

    constructor(private apiService: ApiService) { }

    add = (body: any): Observable<any> => {
        return this.apiService.post(API, body, {});
    };

    viewByUser = (userId: number) : Observable<any> => {
        return this.apiService.get(API + "user/" + userId, { responseType: 'json' });
    }

}
