import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../_services/auth.service';
import { UserService } from '../../_services/user.service';
import { UserResponse } from '../../../../types';

@Component({
    selector: 'app-topbar',
    templateUrl: './topbar.component.html',
    styleUrl: './topbar.component.css',
})
export class TopbarComponent {
    constructor(private router: Router, private authService: AuthService,
        private userService: UserService) { }

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

    goHome() {
        this.router.navigate(['home']);
    }

    ngOnInit() {
        const userId = Number(this.authService.getTokenData()?.userId);

        this.userService.viewById(userId).subscribe({
            next: (res) => {
                this.selectedUser = res.data;
            }
        })
    }
}
