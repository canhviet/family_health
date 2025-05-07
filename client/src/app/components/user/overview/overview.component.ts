import { Component } from '@angular/core';
import { AuthService } from '../../../_services/auth.service';
import { jwtDecode } from 'jwt-decode';
import { JwtPayload } from '../../../../../types';
import { MatDialog } from '@angular/material/dialog';
import { ViewUserRecordComponent } from '../../view-user-record/view-user-record.component';
@Component({
    selector: 'app-overview',
    templateUrl: './overview.component.html',
    styleUrl: './overview.component.css'
})
export class OverviewComponent {

    constructor(private authService: AuthService, public dialog: MatDialog) { }

    openUserRecord() {
        this.dialog.open(ViewUserRecordComponent, {
            width: '90vw',
            height: 'auto',
        });
    }
}
