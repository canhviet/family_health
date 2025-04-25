import { Component } from '@angular/core';
import { AuthService } from '../../_services/auth.service';
import { Role, User } from '../../../../types';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})
export class RegisterComponent {
    user: User = {
        username: '',
        passwordHash: '',
        email: '',
        role: { roleName: '' },
        firstName: '',
        lastName: '',
        dob: '',
        gender: '',
        relationshipToHead: '',
        healthInsuranceCode: '',
        familyId: 0,
        address: '',
        phone: '',
        cityzenId: ''
    };

    roles: Role[] = [
        { roleName: 'doctor' },
        { roleName: 'user' }
    ];

    constructor(private authService: AuthService) { }
    onSubmit() {
        this.authService.register(
            this.user.username,
            this.user.email,
            this.user.passwordHash
        ).subscribe({
            next: data => {
                console.log('Registration successful');
            },
            error: err => {
                console.error('Registration failed', err);
            }
        });
    }

    resetForm(): void {
        this.user = {
            username: '',
            passwordHash: '',
            email: '',
            role: { roleName: '' },
            firstName: '',
            lastName: '',
            dob: '',
            gender: '',
            relationshipToHead: '',
            healthInsuranceCode: '',
            familyId: 0,
            address: '',
            phone: '',
            cityzenId: ''
        };
    }
}
