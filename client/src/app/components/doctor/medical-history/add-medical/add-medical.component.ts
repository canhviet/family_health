import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MedicalHisory, Medication, Prescription } from '../../../../../../types';

@Component({
    selector: 'app-add-medical',
    templateUrl: './add-medical.component.html',
    styleUrl: './add-medical.component.css'
})
export class AddMedicalComponent {
    constructor(public dialogRef: MatDialogRef<AddMedicalComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any) { }


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
        prescriptionDate: new Date,
        doctorUserId: 0,
        userId: 0,
        medications: []
    }


    onSubmit() {
        console.log(this.record);
        console.log(this.prescription);
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
}
