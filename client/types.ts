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
    id?: number;
    username: string;
    passwordHash: string;
    email: string;
    role: Role;
    firstName: string;
    lastName: string;
    dob: string;
    gender: string;
    address: string;
    phone: string;
    relationshipToHead?: string;
    healthInsuranceCode: string;
    familyId: number;
    cityzenId: string;
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

export interface Prescription {
    id?: number;
    notes: string;
    prescriptionDate: Date;
    doctorUserId: number;
    userId: number;
    medications: Medication[];
}

export interface Medication {
    id?: number;
    dosage: string;
    endDate: Date;
    frequency: string;
    instructions: string;
    medicationName: string;
    quantity: string;
    startDate: Date;
}

export interface TestResults {
    id?: number;
    datePerfomed: Date;
    labName: string;
    result: string;
    testType: string;
    userId: number;
}

export interface Immunization {
    id?: number;
    dateAdministered: Date;
    provider: string;
    vaccineName: string;
    userId: number;
}

export interface Allergy {
    id?: number;
    allergen: string;
    reaction: string;
    severity: string;
    userId: number;
}

export interface Family {
    id?: number;
    headOfHouseHoldId: number;
}

export interface Document {
    id?: number;
    documentName: string;
    documentUrl: string;
    uploadDate: Date;
    MedicalHistoryId?: number;
    userId: number;
}


export interface DataResponse {
    status: number;
    message: string;
    data: any;
}

export interface MedicalHisory {
    id?: number;
    condition: string;
    diagnosisDate: Date;
    notes: string;
    revisitDate: Date;
    treatingDoctor: string;
    userId: number;
    doctorUserId: number;
}