import { Component } from '@angular/core';
import { PrescriptionService } from '../../_services/prescription.service';
import { Allergy, Immunization, Prescription, TestResults } from '../../../../types';
import { TestResultService } from '../../_services/test-result.service';
import { ImmunizationService } from '../../_services/immunization.service';
import { AllergyService } from '../../_services/allergy.service';

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

    allgergies: Allergy[] = [];

    immunizations: Immunization[] = [];

    testResults: TestResults[] = []

    constructor(private prescriptionService: PrescriptionService,
        private testResultService: TestResultService,
        private immunizationService: ImmunizationService,
        private allergyService: AllergyService
    ) {}

    ngOnInit() {
        this.prescriptionService.viewByUser(1).subscribe({next: (res) => {
            this.prescriptions = res.data;
            console.log(this.prescriptions);
        }});

        this.allergyService.viewByUser(1).subscribe({next: (res) => {
            this.allgergies = res.data;
            console.log(this.allgergies);
        }});

        this.testResultService.viewByUser(1).subscribe({next: (res) => {
            this.testResults = res.data;
            console.log(this.testResults);
        }});

        this.immunizationService.viewByUser(1).subscribe({next: (res) => {
            this.immunizations= res.data;
            console.log(this.immunizations);
        }});
    }
}
