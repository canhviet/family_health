import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddAllergyComponent } from './add-allergy/add-allergy.component';

@Component({
    selector: 'app-allergies',
    templateUrl: './allergies.component.html',
    styleUrl: './allergies.component.css'
})
export class AllergiesComponent {
    constructor(public dialog: MatDialog) { }

    addAllergy() {
        this.dialog.open(AddAllergyComponent, {

        });
    }
}
