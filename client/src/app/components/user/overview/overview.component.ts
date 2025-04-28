import { Component } from '@angular/core';
import { AuthService } from '../../../_services/auth.service';
import { jwtDecode } from 'jwt-decode';
import { JwtPayload } from '../../../../../types';
@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrl: './overview.component.css'
})
export class OverviewComponent {

    constructor(private authService: AuthService){}

    viewDoctorApp: boolean = false;

    ngOnInit() {
        const token = this.authService.getTokenData();

        if (token) {
            const decoded = jwtDecode<JwtPayload>(token.accessToken);
            console.log(decoded.permissions);
            if(decoded.permissions.includes('FULL_ACCESS') || decoded.permissions.includes('DOCTOR')) {
                this.viewDoctorApp = true;
            }
        }
    }
}
