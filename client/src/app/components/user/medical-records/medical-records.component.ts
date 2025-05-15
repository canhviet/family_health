import { Component } from '@angular/core';
import { Allergy, Immunization, MedicalHistoryResponse, PrescriptionResponse, TestResponse } from '../../../../../types';
import { PrescriptionService } from '../../../_services/prescription.service';
import { AllergyService } from '../../../_services/allergy.service';
import { ImmunizationService } from '../../../_services/immunization.service';
import { TestResultService } from '../../../_services/test-result.service';
import { AuthService } from '../../../_services/auth.service';
import { HistoryService } from '../../../_services/history.service';
import { ReportService } from '../../../_services/report.service';

@Component({
    selector: 'app-medical-records',
    templateUrl: './medical-records.component.html',
    styleUrl: './medical-records.component.css'
})
export class MedicalRecordsComponent {
    tabs = [
        { label: 'Medical Checkup' },
        { label: 'Prescriptions' },
        { label: 'Immunizations' },
        { label: 'Allergies' },
        { label: 'Tests' }
    ];

    selectedTabIndex = 0;


    prescriptions: PrescriptionResponse[] = [];

    allgergies: Allergy[] = [];

    immunizations: Immunization[] = [];

    testResults: TestResponse[] = [];

    medicalHistories: MedicalHistoryResponse[] = [];

    constructor(private prescriptionService: PrescriptionService,
        private testResultService: TestResultService,
        private immunizationService: ImmunizationService,
        private allergyService: AllergyService,
        private authService: AuthService,
        private historyService: HistoryService,
        private reportService: ReportService
    ) { }

    ngOnInit() {
        const userId = Number(this.authService.getTokenData()?.userId);

        this.historyService.viewByUser(userId).subscribe({
            next: (res) => {
                this.medicalHistories = res.data;
            }
        })

        this.prescriptionService.viewByUser(userId).subscribe({
            next: (res) => {
                this.prescriptions = res.data;
            }
        });

        this.allergyService.viewByUser(userId).subscribe({
            next: (res) => {
                this.allgergies = res.data;
            }
        });

        this.testResultService.viewByUser(userId).subscribe({
            next: (res) => {
                this.testResults = res.data;
            }
        });

        this.immunizationService.viewByUser(userId).subscribe({
            next: (res) => {
                this.immunizations = res.data;
            }
        });
    }

    printPrescription(prescriptionId: number) {
        this.reportService.prescription(prescriptionId).subscribe({
            next: (blob: Blob) => {
                const url = window.URL.createObjectURL(blob);
                const a = document.createElement('a');
                a.href = url;
                a.download = `prescription_${prescriptionId}.pdf`; // Match server’s filename
                a.click();
                window.URL.revokeObjectURL(url);
            },
            error: (err) => {
                console.error('Error downloading PDF:', err);
            }
        });
    }

    printTestResult(testId: number) {
        this.reportService.test(testId).subscribe({
            next: (blob: Blob) => {
                const url = window.URL.createObjectURL(blob);
                const a = document.createElement('a');
                a.href = url;
                a.download = `test_${testId}.pdf`;
                a.click();
                window.URL.revokeObjectURL(url);
            },
            error: (err) => {
                console.error('Error downloading PDF:', err);
            }
        });
    }
}
