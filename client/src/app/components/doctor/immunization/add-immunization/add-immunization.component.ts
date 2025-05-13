import { Component, Inject } from '@angular/core';
import { ImmunizationService } from '../../../../_services/immunization.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Immunization } from '../../../../../../types';

@Component({
  selector: 'app-add-immunization',
  templateUrl: './add-immunization.component.html',
  styleUrl: './add-immunization.component.css'
})
export class AddImmunizationComponent {
    constructor(
        private immunizationService: ImmunizationService,
        public dialogRef: MatDialogRef<AddImmunizationComponent>,
        @Inject(MAT_DIALOG_DATA) public data: number) { }

    record: Immunization = {
        dateAdministered: new Date,
        provider: '',
        vaccineName: '',
        userId: this.data
    }

    onSubmit() {
        this.immunizationService.add(this.record).subscribe();
    }

    onClose() {
        this.dialogRef.close();
    }
}
