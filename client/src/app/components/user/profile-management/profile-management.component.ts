import { Component } from '@angular/core';
import { UserResponse, UserUpdateRequest } from '../../../../../types';
import { AuthService } from '../../../_services/auth.service';
import { UserService } from '../../../_services/user.service';

@Component({
    selector: 'app-profile-management',
    templateUrl: './profile-management.component.html',
    styleUrl: './profile-management.component.css'
})
export class ProfileManagementComponent {
    constructor(private authService: AuthService, private userService: UserService) { }

    ngOnInit() {
        const userId = Number(this.authService.getTokenData()?.userId);

        this.userService.viewById(userId).subscribe({
            next: (res) => {
                this.selectedUser = res.data;
            }
        })
    }

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
    };

    save() {
        const user: UserUpdateRequest = {
            email: this.selectedUser.email,
            firstName: this.selectedUser.firstName,
            lastName: this.selectedUser.lastName,
            dob: this.selectedUser.dob,
            gender: this.selectedUser.gender,
            address: this.selectedUser.address,
            phone: this.selectedUser.phone,
            healthInsuranceCode: this.selectedUser.healthInsuranceCode,
            cityzenId: this.selectedUser.cityzenId
        }

        this.userService.update(this.selectedUser.userId, user).subscribe();
    }
}
