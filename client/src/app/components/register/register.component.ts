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
    contactInfo: '',
    specialty: '',
    relationshipToHead: ''
  };

  roles: Role[] = [
    { roleName: 'doctor' },
    { roleName: 'user' }
  ];

  constructor(private authService: AuthService) { }

  onSubmit(): void {
    if (!this.user.username || !this.user.passwordHash || !this.user.firstName || !this.user.lastName || !this.user.role.roleName) {
      alert('Vui lòng điền các trường bắt buộc!');
      return;
    }

    // this.authService.register(this.user).subscribe(
    //   response => {
    //     console.log('Registration successful', response);
    //     this.resetForm();
    //   },
    //   error => {
    //     console.error('Registration failed', error);
    //   }
    // );
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
      contactInfo: '',
      specialty: '',
      relationshipToHead: ''
    };
  }
}