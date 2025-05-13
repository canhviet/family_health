import { Component, Inject } from '@angular/core';
import { TestResults } from '../../../../../../types';
import { TestResultService } from '../../../../_services/test-result.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-add-test',
  templateUrl: './add-test.component.html',
  styleUrl: './add-test.component.css'
})
export class AddTestComponent {
    constructor(
        private testResultService: TestResultService,
        public dialogRef: MatDialogRef<AddTestComponent>,
        @Inject(MAT_DIALOG_DATA) public data: number) { }

    record: TestResults = {
        datePerfomed: new Date,
        labName: '',
        result: '',
        testType: '',
        userId: this.data
    }

    onSubmit() {
        this.testResultService.add(this.record).subscribe();
    }

    onClose() {
        this.dialogRef.close();
    }
}
