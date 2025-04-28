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
    permissions: string[];
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
    prescriptionId?: number;
    notes: string;
    prescriptionDate: Date;
    doctorUserId: number;
    userId: number;
    medications: Medication[];
}

export interface Medication {
    medicationId?: number;
    dosage: string;
    endDate: Date;
    frequency: string;
    instructions: string;
    medicationName: string;
    quantity: string;
    startDate: Date;
}

export interface TestResults {
    testId?: number;
    datePerfomed: Date;
    labName: string;
    result: string;
    testType: string;
    userId: number;
}

export interface Immunization {
    immunizationId?: number;
    dateAdministered: Date;
    provider: string;
    vaccineName: string;
    userId: number;
}

export interface Allergy {
    AlleryId?: number;
    allergen: string;
    reaction: string;
    severity: string;
    userId: number;
}

export interface Family {
    familyId?: number;
    headOfHouseHoldId: number;
}

export interface Document {
    documentId?: number;
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
    historyId?: number;
    condition: string;
    diagnosisDate: Date;
    notes: string;
    revisitDate: Date;
    treatingDoctor: string;
    userId: number;
    doctorUserId: number;
}

export interface ResetPassword {
    password: String;
    confirmPassword: String;
    secretKey: String;
}

export interface Register {
    username: string;
    password: string;
    firstName: string;
    lastName: string;
    role: string;
}