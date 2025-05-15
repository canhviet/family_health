import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';

const API = 'http://localhost:8083/api/reports/';

@Injectable({
    providedIn: 'root'
})
export class ReportService {

    constructor(private apiService: ApiService) { }

    test(testId: number): Observable<Blob> {
        return this.apiService.get<Blob>(`${API}test-result?testResultId=${testId}`, {
            responseType: 'blob',
            observe: 'body'
        });
    }

    prescription(prescriptionId: number): Observable<Blob> {
        return this.apiService.get<Blob>(`${API}prescription?prescriptionId=${prescriptionId}`, {
            responseType: 'blob',
            observe: 'body'
        });
    }
}
