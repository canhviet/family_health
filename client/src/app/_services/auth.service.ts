import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { JwtPayload, TokenResponse } from '../../../types';
import { CookieService } from 'ngx-cookie-service';
import { jwtDecode } from 'jwt-decode';

const AUTH_API = 'http://localhost:8083/auth/';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
    providedIn: 'root',
})
export class AuthService {
    constructor(private apiService: ApiService, private cookieService: CookieService) {
        this.loadTokenFromCookie();
    }

    private tokenData: TokenResponse | null = null;

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
        data: any
    ): Observable<any> {
        return this.apiService.post(
            AUTH_API + 'register',
            data,
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

    changePassword(data: any): Observable<any> {
        return this.apiService.post(AUTH_API + 'change-password', data, {
            responseType: 'text' as 'json',
        });
    }

    setTokenData(data: TokenResponse) {
        this.tokenData = data;
        this.cookieService.set('token', JSON.stringify(data), 7);
    }

    getTokenData(): TokenResponse | null {
        return this.tokenData;
    }

    removeToken() {
        this.cookieService.delete('token');
    }

    private loadTokenFromCookie() {
        const token = this.cookieService.get('token');
        if (token) {
            this.tokenData = JSON.parse(token);
        }
    }

    isTokenExpired(): boolean {
        const tokenData = this.getTokenData();
        if (!tokenData) {
            return true; // Không có token -> coi như hết hạn
        }

        try {
            const decoded = jwtDecode<JwtPayload>(tokenData.accessToken);
            const currentTime = Math.floor(Date.now() / 1000);
            return currentTime >= decoded.exp;
        } catch (error) {
            console.error('Invalid token:', error);
            return true;
        }
    }
}


