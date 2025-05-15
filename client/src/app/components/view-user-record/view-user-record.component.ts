import { Component, Inject } from '@angular/core';
import { PrescriptionService } from '../../_services/prescription.service';
import { Allergy, Document, Immunization, MedicalHistoryResponse, PrescriptionResponse, TestResponse } from '../../../../types';
import { TestResultService } from '../../_services/test-result.service';
import { ImmunizationService } from '../../_services/immunization.service';
import { AllergyService } from '../../_services/allergy.service';
import { AuthService } from '../../_services/auth.service';
import { HistoryService } from '../../_services/history.service';
import { ReportService } from '../../_services/report.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DocumentService } from '../../_services/document.service';

@Component({
    selector: 'app-view-user-record',
    templateUrl: './view-user-record.component.html',
    styleUrl: './view-user-record.component.css'
})
export class ViewUserRecordComponent {
    tabs = [
        { label: 'Medical Checkup' },
        { label: 'Prescriptions' },
        { label: 'Immunizations' },
        { label: 'Allergies' },
        { label: 'Tests' },
        { label: 'Documents' }
    ];

    selectedTabIndex = 0;


    prescriptions: PrescriptionResponse[] = [];

    allgergies: Allergy[] = [];

    immunizations: Immunization[] = [];

    testResults: TestResponse[] = [];

    medicalHistories: MedicalHistoryResponse[] = [];

    documents: Document[] = [];

    constructor(private prescriptionService: PrescriptionService,
        private testResultService: TestResultService,
        private immunizationService: ImmunizationService,
        private allergyService: AllergyService,
        private authService: AuthService,
        private historyService: HistoryService,
        private reportService: ReportService,
        public dialogRef: MatDialogRef<ViewUserRecordComponent>,
        private documentService: DocumentService,
        @Inject(MAT_DIALOG_DATA) public data: number
    ) { }

    ngOnInit() {
        const userId = this.data;

        this.documentService.getByUser(userId).subscribe({
            next: (res) => {
                this.documents = res.data;
            }
        })

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

    openDocument(url: string): void {
        if (url) {
            window.open(url, '_blank');
        }
    }
}
