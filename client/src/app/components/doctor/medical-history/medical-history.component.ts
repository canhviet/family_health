import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddMedicalComponent } from './add-medical/add-medical.component';

@Component({
    selector: 'app-medical-history',
    templateUrl: './medical-history.component.html',
    styleUrl: './medical-history.component.css'
})
export class MedicalHistoryComponent {

    constructor(public dialog: MatDialog) { }


    addMedicalRecord() {
        this.dialog.open(AddMedicalComponent, {
            width: '90vw',
            height: 'auto',
        });
    }

}
