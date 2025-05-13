import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Allergy } from '../../../../../../types';
import { AllergyService } from '../../../../_services/allergy.service';

@Component({
    selector: 'app-add-allergy',
    templateUrl: './add-allergy.component.html',
    styleUrl: './add-allergy.component.css'
})
export class AddAllergyComponent {
    constructor(
        private allergyService: AllergyService,
        public dialogRef: MatDialogRef<AddAllergyComponent>,
        @Inject(MAT_DIALOG_DATA) public data: number) { }

    record: Allergy = {
        allergen: '',
        reaction: '',
        severity: '',
        userId: this.data
    }

    onSubmit() {
        this.allergyService.add(this.record).subscribe();
    }

    onClose() {
        this.dialogRef.close();
    }
}
