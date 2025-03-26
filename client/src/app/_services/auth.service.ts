import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';

const AUTH_API = 'http://localhost:8083/auth/';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
    providedIn: 'root',
})
export class AuthService {
    constructor(private apiService: ApiService) {}

    login(username: String, password: String): Observable<any> {
        return this.apiService.post(
            AUTH_API + 'access-token',
            {
                username,
                password,
            },
            httpOptions
        );
    }

    refresh(refresh_token: string): Observable<any> {
        return this.apiService.post(AUTH_API + 'refresh-token', {}, {});
    }

    register(
        username: string,
        email: string,
        password: string
    ): Observable<any> {
        return this.apiService.post(
            AUTH_API + 'register',
            {
                username,
                email,
                password,
            },
            httpOptions
        );
    }

    logout(): Observable<any> {
        return this.apiService.post(AUTH_API + 'remove-token', {}, httpOptions);
    }

    forgotPassword(data: String): Observable<any> {
        return this.apiService.post(AUTH_API + 'forgot-password', data, {
            responseType: 'text' as 'json',
        });
    }

    resetPassword(data: String): Observable<any> {
        return this.apiService.post(AUTH_API + 'reset-password', data, {
            responseType: 'text' as 'json',
        });
    }

    changePassword(data: any): Observable<any> {
        return this.apiService.post(AUTH_API + 'change-password', data, {
            responseType: 'text' as 'json',
        });
    }
}


