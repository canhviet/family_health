import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Allergy } from '../../../../../../types';

@Component({
    selector: 'app-add-allergy',
    templateUrl: './add-allergy.component.html',
    styleUrl: './add-allergy.component.css'
})
export class AddAllergyComponent {
    constructor(public dialogRef: MatDialogRef<AddAllergyComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any) { }

    record: Allergy = {
        allergen: '',
        reaction: '',
        severity: '',
        userId: 0
    }

    onSubmit() { }
    
    onClose() {
        this.dialogRef.close();
    }
}
