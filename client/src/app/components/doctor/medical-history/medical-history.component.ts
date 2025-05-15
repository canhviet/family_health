import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddMedicalComponent } from './add-medical/add-medical.component';
import { DataResponse, UserResponse } from '../../../../../types';
import { ConnectionService } from '../../../_services/connection.service';
import { AuthService } from '../../../_services/auth.service';
import { ViewUserRecordComponent } from '../../view-user-record/view-user-record.component';

@Component({
    selector: 'app-medical-history',
    templateUrl: './medical-history.component.html',
    styleUrl: './medical-history.component.css'
})
export class MedicalHistoryComponent {

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
        this.searchTerm = '';
        this.filteredItems = [];
    }

    addMedicalRecord() {
        this.dialog.open(AddMedicalComponent, {
            width: '90vw',
            height: 'auto',
            data: this.selectedUser.userId
        });
    }

    openRecords(userId: number) {
        this.dialog.open(ViewUserRecordComponent, {
            width: '90vw',
            height: 'auto',
            data: userId
        });
    }

}
