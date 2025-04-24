import { Component } from '@angular/core';
import { User } from '../../../../../types';

@Component({
  selector: 'app-immunization',
  templateUrl: './immunization.component.html',
  styleUrl: './immunization.component.css'
})
export class ImmunizationComponent {
    selectedUser: User = {
        username: '',
        passwordHash: '',
        email: '',
        firstName: '',
        lastName: '',
        dob: '',
        gender: '',
        address: '',
        phone: '',
        healthInsuranceCode: '',
        familyId: 0,
        role: { roleName: '' },
        cityzenId: ''
    };
}
