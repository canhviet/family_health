import { Component } from '@angular/core';
import { User } from '../../../../../types';

@Component({
  selector: 'app-test-result',
  templateUrl: './test-result.component.html',
  styleUrl: './test-result.component.css'
})
export class TestResultComponent {
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
