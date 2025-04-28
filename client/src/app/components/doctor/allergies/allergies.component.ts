import { Component } from '@angular/core';
import { User } from '../../../../../types';
import { MatDialog } from '@angular/material/dialog';
import { AddAllergyComponent } from './add-allergy/add-allergy.component';

@Component({
    selector: 'app-allergies',
    templateUrl: './allergies.component.html',
    styleUrl: './allergies.component.css'
})
export class AllergiesComponent {
    constructor(public dialog: MatDialog) { }
    selectedUser: User = {
        username: '',
        passwordHash: '',
        email: '',
        firstName: '',
        lastName: '',
        dob: '',
        gender: '',
        address: '',
        phone: '',
        healthInsuranceCode: '',
        familyId: 0,
        role: { roleName: '' },
        cityzenId: ''
    };

    addAllergy() {
        this.dialog.open(AddAllergyComponent, {

        });
    }
}
