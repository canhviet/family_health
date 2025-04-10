import { HttpContext, HttpHeaders, HttpParams } from '@angular/common/http';

export interface Options {
    headers?:
    | HttpHeaders
    | {
        [header: string]: string | string[];
    };
    observe?: 'body';
    context?: HttpContext;
    params?:
    | HttpParams
    | {
        [param: string]:
        | string
        | number
        | boolean
        | ReadonlyArray<string | number | boolean>;
    };
    reportProgress?: boolean;
    responseType?: 'json';
    withCredentials?: boolean;
    transferCache?:
    | {
        includeHeaders?: string[];
    }
    | boolean;
}

export interface PaginationParams {
    [param: string]:
    | string
    | number
    | boolean
    | ReadonlyArray<string | number | boolean>;
    pageNo: number;
    pageSize: number;
}

export interface SignInRequest {
    username: String;
    password: String;
}

export interface JwtPayload {
    roles: string[];
    username: string;
    sub: string;
    iat: number;
    exp: number;
}

export interface User {
    userId?: number;
    username: string;
    passwordHash: string;
    email: string;
    role: Role;
    firstName: string;
    lastName: string;
    dob: string;
    gender: string;
    contactInfo: string;
    specialty?: string;
    relationshipToHead?: string;
}

export interface Role {
    roleId?: number;
    roleName: string;
}

export interface TokenResponse {
    userId: string;
    accessToken: string;
    refreshToken: string;
}