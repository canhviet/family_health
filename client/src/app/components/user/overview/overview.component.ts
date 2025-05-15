import { Component } from '@angular/core';
import { AuthService } from '../../../_services/auth.service';
import { jwtDecode } from 'jwt-decode';
import { JwtPayload, MedicalHistory, MedicalHistoryResponse, Medication, UserResponse } from '../../../../../types';
import { MatDialog } from '@angular/material/dialog';
import { ViewUserRecordComponent } from '../../view-user-record/view-user-record.component';
import { UserService } from '../../../_services/user.service';
import { PrescriptionService } from '../../../_services/prescription.service';
import { HistoryService } from '../../../_services/history.service';
@Component({
    selector: 'app-overview',
    templateUrl: './overview.component.html',
    styleUrl: './overview.component.css'
})
export class OverviewComponent {

    constructor(private authService: AuthService, public dialog: MatDialog,
        private userService: UserService, private prescriptionService: PrescriptionService,
        private historyService: HistoryService
    ) { }

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
    }

    medications: Medication[] = [];

    revisit: MedicalHistoryResponse = {
        condition: '',
        diagnosisDate: new Date,
        notes: '',
        revisitDate: new Date,
        treatingDoctor: '',
        userId: 0,
        doctorName: '',
        historyId: 0
    }

    ngOnInit() {
        const userId = Number(this.authService.getTokenData()?.userId);

        this.userService.viewById(userId).subscribe({
            next: (res) => {
                this.selectedUser = res.data;
            }
        })

        this.prescriptionService.reminder(userId).subscribe({
            next: (res) => {
                this.medications = res.data;
            }
        })

        this.historyService.reminder(userId).subscribe({
            next: (res) => {
                this.revisit = res.data;
            }
        })
    }


}
