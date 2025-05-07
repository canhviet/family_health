import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';

const API = 'http://localhost:8083/api/family/';

@Injectable({
    providedIn: 'root'
})
export class FamilyService {

    constructor(private apiService: ApiService) { }

    addNewMember = (body: any): Observable<any> => {
        return this.apiService.post(API + "add-new-member", body, {});
    }

    viewFamilyOfUser = (familyId: number): Observable<any> => {
        return this.apiService.get(API + "user/" + familyId, { responseType: 'json' });
    }

    searchUser = (userId: number, search: string): Observable<any> => {
        return this.apiService.get(API + userId + "/" + search, { responseType: 'json' });
    }
}
