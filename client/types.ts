import { HttpHeaders, HttpContext, HttpParams } from '@angular/common/http';

export interface Options {
    headers?:
        | HttpHeaders
        | {
              [header: string]: string | string[];
          };
    observe?: 'body' | 'events' | 'response'; // Include all possible observe values
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
    responseType?: 'json' | 'blob' | 'arraybuffer' | 'text'; // Include all HttpClient response types
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

export interface UserResponse {
    userId: number;
    username: string;
    email: string;
    firstName: string;
    lastName: string;
    dob: Date;
    gender: string;
    address: string;
    phone: string;
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

export interface PrescriptionResponse {
    prescriptionId: number;
    notes: string;
    prescriptionDate: Date;
    doctorName: string;
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

export interface TestResponse {
    testId: number;
    datePerformed: Date;
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
    alleryId?: number;
    allergen: string;
    reaction: string;
    severity: string;
    userId: number;
}

export interface Family {
    familyId?: number;
    headId: number;
}

export interface AddNewMember {
    relationship: string;
    headId: number;
    userId: number;
}

export interface Document {
    documentId?: number;
    documentName: string;
    documentUrl: string;
    uploadDate: Date;
    userId: number;
}


export interface DataResponse {
    status: number;
    message: string;
    data: any;
}

export interface MedicalHistory {
    condition: string;
    diagnosisDate: Date;
    notes: string;
    revisitDate: Date;
    treatingDoctor: string;
    userId: number;
    doctorUserId: number;
}

export interface MedicalHistoryResponse {
    historyId: number;
    condition: string;
    diagnosisDate: Date;
    notes: string;
    revisitDate: Date;
    treatingDoctor: string;
    userId: number;
    doctorName: string;
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

export interface UserInFamily {
    relationshipToHead: string;
    userId: number;
    firstName: string;
    lastName: string;
}

export interface ConnectedUsers {
    username: string;
    firstName: string;
    lastName: string;
    userId: number;
    connectAt: Date;
}

export interface UserSearch {
    username: string;
    firstName: string;
    lastName: string;
    userId: number;
}

export interface AddConnection {
    userId: number;
    doctorId: number;
}

export interface UserUpdateRequest {
    email: string;
    firstName: string;
    lastName: string;
    dob: Date;
    gender: string;
    address: string;
    phone: string;
    healthInsuranceCode: string;
    cityzenId: string;
}