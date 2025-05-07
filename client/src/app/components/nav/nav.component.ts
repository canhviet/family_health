import { Component } from '@angular/core';
import { AuthService } from '../../_services/auth.service';
import { JwtPayload } from '../../../../types';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css'
})
export class NavComponent {
    constructor(private authService: AuthService) {}

    canViewDoctorApp: boolean = false;

    ngOnInit() {
        const token = this.authService.getTokenData();

        if (token) {
            const decoded = jwtDecode<JwtPayload>(token.accessToken);
            console.log(decoded.permissions);
            if (decoded.permissions.includes('FULL_ACCESS') || decoded.permissions.includes('DOCTOR')) {
                this.canViewDoctorApp = true;
            }
        }
    }
}
