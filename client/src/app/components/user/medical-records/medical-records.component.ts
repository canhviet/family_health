import { Component } from '@angular/core';
import { Allergy, Immunization, Prescription, TestResults } from '../../../../../types';
import { PrescriptionService } from '../../../_services/prescription.service';
import { AllergyService } from '../../../_services/allergy.service';
import { ImmunizationService } from '../../../_services/immunization.service';
import { TestResultService } from '../../../_services/test-result.service';

@Component({
    selector: 'app-medical-records',
    templateUrl: './medical-records.component.html',
    styleUrl: './medical-records.component.css'
})
export class MedicalRecordsComponent {
    tabs = [
        { label: 'Tab 1' },
        { label: 'Tab 2' },
        { label: 'Tab 3' }
    ];

    selectedTabIndex = 0;


    prescriptions: Prescription[] = [];

    allgergies: Allergy[] = [];

    immunizations: Immunization[] = [];

    testResults: TestResults[] = []

    constructor(private prescriptionService: PrescriptionService,
        private testResultService: TestResultService,
        private immunizationService: ImmunizationService,
        private allergyService: AllergyService
    ) { }

    ngOnInit() {
        this.prescriptionService.viewByUser(3).subscribe({
            next: (res) => {
                this.prescriptions = res.data;
                console.log(this.prescriptions);
            }
        });

        this.allergyService.viewByUser(1).subscribe({
            next: (res) => {
                this.allgergies = res.data;
                console.log(this.allgergies);
            }
        });

        this.testResultService.viewByUser(1).subscribe({
            next: (res) => {
                this.testResults = res.data;
                console.log(this.testResults);
            }
        });

        this.immunizationService.viewByUser(1).subscribe({
            next: (res) => {
                this.immunizations = res.data;
                console.log(this.immunizations);
            }
        });
    }
}
