import { Component } from '@angular/core';
import { PrescriptionService } from '../../_services/prescription.service';
import { Prescription } from '../../../../types';

@Component({
    selector: 'app-view-user-record',
    templateUrl: './view-user-record.component.html',
    styleUrl: './view-user-record.component.css'
})
export class ViewUserRecordComponent {
    selectedTabIndex = 0;

    onTabChange(index: number) {
        this.selectedTabIndex = index;
    }

    prescriptions: Prescription[] = [];

    constructor(private prescriptionService: PrescriptionService) {}

    ngOnInit() {
        this.prescriptionService.viewByUser(3).subscribe({next: (res) => {
            this.prescriptions = res.data;
            console.log(this.prescriptions);
        }});
    }
}
