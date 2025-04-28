import { Component } from '@angular/core';
import { AuthService } from '../../_services/auth.service';
import { Register } from '../../../../types';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})
export class RegisterComponent {
        ;

    constructor(private authService: AuthService,
        private toastr: ToastrService,
        private router: Router
    ) { }

    showPassword: boolean = false;

    togglePassword(): void {
        this.showPassword = !this.showPassword;
    }

    user: Register = {
        username: '',
        password: '',
        firstName: '',
        lastName: '',
        role: ''
    }

    onSubmit(): void {
        this.authService.register(this.user).subscribe(() => {
            this.toastr.success("Register Successfully!")
            this.router.navigate(['']);
        });
    }

}