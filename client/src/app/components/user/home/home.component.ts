import { Component } from '@angular/core';
import { AuthService } from '../../../_services/auth.service';
import { UserService } from '../../../_services/user.service';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrl: './home.component.css'
})
export class HomeComponent {
    constructor(private authSerice: AuthService, private userService: UserService) { }

    ngOnInit() {
        const userId = Number(this.authSerice.getTokenData()?.userId);
    }
}
