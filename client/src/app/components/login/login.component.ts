import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SignInRequest, TokenResponse } from '../../../../types';
import { AuthService } from '../../_services/auth.service';
import { ToastrService } from 'ngx-toastr';


@Component({
    selector: 'app-login',
    standalone: false,
    templateUrl: './login.component.html',
    styleUrl: './login.component.css',
})
export class LoginComponent {
    constructor(
        private router: Router,
        private authService: AuthService,
        private toastr: ToastrService
    ) { }

    showPassword: boolean = false;


    signInRequest: SignInRequest = {
        username: '',
        password: '',
    };

    ngOnInit() {}

    onSubmit(form: any) {
        if (form.valid) {
            this.authService
                .login(this.signInRequest.username, this.signInRequest.password)
                .subscribe({
                    next: (data: TokenResponse) => {
                        this.authService.removeToken();
                        this.authService.setTokenData(data);
                        this.toastr.success('Đăng nhập thành công!', 'Thành công');

                        sessionStorage.setItem('userId', data.userId);
                        this.router.navigate(['home']);
                    },
                    error: (error) => {
                        this.toastr.error('Đăng nhập không thành công!', error);
                    },
                });
        }
    }

    redirectRegister() {
        this.router.navigate(['sign_up']);
    }

    redirectForgot() {
        this.router.navigate(['forgot-password']);
    }
}
