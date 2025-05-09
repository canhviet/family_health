import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FamilyService } from '../../../../_services/family.service';
import { AddNewMember } from '../../../../../../types';

@Component({
    selector: 'app-add-member',
    templateUrl: './add-member.component.html',
    styleUrl: './add-member.component.css'
})
export class AddMemberComponent {
    constructor(
        private familyService: FamilyService,
        @Inject(MAT_DIALOG_DATA) public data: any,
        public dialogRef: MatDialogRef<AddMemberComponent>,
    ) { }

    request: AddNewMember = {
        relationship: '',
        headId: this.data.headId,
        userId: this.data.userId
    }

    onCancel(): void {
        this.dialogRef.close();
    }

    add() {
        console.log(this.request);

        this.familyService.addNewMember(this.request).subscribe(() => {
            this.onCancel;
        })
    }
}
