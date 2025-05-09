import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MedicalHisory, Medication, Prescription } from '../../../../../../types';
import { PrescriptionService } from '../../../../_services/prescription.service';
import { UploadComponent } from '../../../upload/upload.component';

@Component({
    selector: 'app-add-medical',
    templateUrl: './add-medical.component.html',
    styleUrl: './add-medical.component.css'
})
export class AddMedicalComponent {
    constructor(public dialogRef: MatDialogRef<AddMedicalComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any,
        private prescriptionService: PrescriptionService,
        private dialog: MatDialog) { }

    record: MedicalHisory = {
        condition: '',
        diagnosisDate: new Date,
        notes: '',
        revisitDate: new Date,
        treatingDoctor: '',
        userId: 0,
        doctorUserId: 0
    };

    prescription: Prescription = {
        notes: '',
        prescriptionDate: new Date(),
        doctorUserId: Number(2),
        userId: Number(3),
        medications: []
    }


    onSubmit() {
        this.prescriptionService.add(this.prescription).subscribe(() => { this.onClose });
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
        this.dialog.open(UploadComponent, {
            width: '500px'
        });
    }
}
