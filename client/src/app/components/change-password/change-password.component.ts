import { Component, Input } from '@angular/core';
import { AuthService } from '../../_services/auth.service';
import { Router } from '@angular/router';
import { ResetPassword } from '../../../../types';

@Component({
    selector: 'app-change-password',
    templateUrl: './change-password.component.html',
    styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent {
    constructor(private authService: AuthService, private router: Router) { }

    @Input() data: ResetPassword = {
        password: '',
        confirmPassword: '',
        secretKey: '',
    };

    notify: boolean = false;

    showPassword: boolean = false;
    showConfirmPassword: boolean = false;

    ngOnInit() {
        const urlParams = new URLSearchParams(window.location.search);
        const token = urlParams.get('token');

        if (token) {
            this.data.secretKey = token;
        }
    }

    togglePassword(field: string): void {
        if (field === 'password') {
            this.showPassword = !this.showPassword;
        } else if (field === 'confirmPassword') {
            this.showConfirmPassword = !this.showConfirmPassword;
        }
    }

    onSubmit() {
        this.authService.changePassword(this.data).subscribe({
            next: () => {
                this.router.navigate(['']);
            },
        });
    }
}
