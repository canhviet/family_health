import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MedicalHistory, Medication, Prescription } from '../../../../../../types';
import { PrescriptionService } from '../../../../_services/prescription.service';
import { UploadComponent } from '../../../upload/upload.component';
import { AuthService } from '../../../../_services/auth.service';
import { HistoryService } from '../../../../_services/history.service';

@Component({
    selector: 'app-add-medical',
    templateUrl: './add-medical.component.html',
    styleUrl: './add-medical.component.css'
})
export class AddMedicalComponent {
    constructor(public dialogRef: MatDialogRef<AddMedicalComponent>,
        @Inject(MAT_DIALOG_DATA) public data: Number,
        private prescriptionService: PrescriptionService,
        private authService: AuthService,
        private historyService: HistoryService,
        private dialog: MatDialog) { }

    record: MedicalHistory = {
        condition: '',
        diagnosisDate: new Date,
        notes: '',
        revisitDate: new Date,
        treatingDoctor: '',
        userId: Number(this.data),
        doctorUserId: Number(this.authService.getTokenData()?.userId)
    };

    prescription: Prescription = {
        notes: '',
        prescriptionDate: new Date(),
        doctorUserId: Number(this.authService.getTokenData()?.userId),
        userId: Number(this.data),
        medications: []
    }


    onSubmit() {
        this.historyService.add(this.record).subscribe(() => {
            this.prescriptionService.add(this.prescription).subscribe(() => { this.onClose });
        })
    }

    onClose(): void {
        this.dialogRef.close();
    }

    addMedication() {
        let newMedication: Medication = {
            dosage: '',
            endDate: new Date,
            frequency: '',
            instructions: '',
            medicationName: '',
            quantity: '',
            startDate: new Date
        }

        this.prescription.medications.push(newMedication);
    }

    onUpload(): void {
        const id = this.prescription.userId;
        this.dialog.open(UploadComponent, {
            width: '500px',
            data: id
        });
    }
}
