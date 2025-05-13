import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UserResponse, DataResponse } from '../../../../../types';
import { AuthService } from '../../../_services/auth.service';
import { ConnectionService } from '../../../_services/connection.service';
import { AddImmunizationComponent } from './add-immunization/add-immunization.component';

@Component({
  selector: 'app-immunization',
  templateUrl: './immunization.component.html',
  styleUrl: './immunization.component.css'
})
export class ImmunizationComponent {
    constructor(public dialog: MatDialog,
        private connectionService: ConnectionService,
        private authService: AuthService
    ) { }

    doctorId: number = 0

    selectedUser: UserResponse = {
        userId: 0,
        username: '',
        email: '',
        firstName: '',
        lastName: '',
        dob: new Date,
        gender: '',
        address: '',
        phone: '',
        healthInsuranceCode: '',
        familyId: 0,
        cityzenId: ''
    };

    ngOnInit() {
        this.doctorId = Number(this.authService.getTokenData()?.userId);
    }

    searchTerm: string = '';
    filteredItems: UserResponse[] = [];


    onSearch() {
        if (this.searchTerm.trim()) {
            this.connectionService.searchPatients(this.doctorId, this.searchTerm).subscribe({
                next: (res: DataResponse) => {
                    this.filteredItems = res.data;
                }
            })
        } else {
            this.filteredItems = [];
        }
    }

    select(user: UserResponse) {
        this.selectedUser = user;
    }

    addMedicalRecord() {
        this.dialog.open(AddImmunizationComponent, {
            width: '30vw',
            height: 'auto',
            data: this.selectedUser.userId
        });
    }
}
