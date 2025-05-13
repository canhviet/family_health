import { Component } from '@angular/core';
import { AuthService } from '../../_services/auth.service';
import { JwtPayload } from '../../../../types';
import { jwtDecode } from 'jwt-decode';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css'
})
export class NavComponent {
    constructor(private authService: AuthService, private router: Router) {}

    canViewDoctorApp: boolean = false;

    ngOnInit() {
        const token = this.authService.getTokenData();

        if (token) {
            const decoded = jwtDecode<JwtPayload>(token.accessToken);
            if (decoded.permissions.includes('FULL_ACCESS')) {
                this.canViewDoctorApp = true;
            }
        }
    }

    logout() {
        this.authService.removeToken();
        this.router.navigate(['login']);
    }
}
