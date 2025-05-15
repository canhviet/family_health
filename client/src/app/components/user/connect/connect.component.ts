import { Component } from '@angular/core';
import { ConnectionService } from '../../../_services/connection.service';
import { AddConnection, ConnectedUsers, UserSearch } from '../../../../../types';
import { AuthService } from '../../../_services/auth.service';

@Component({
    selector: 'app-connect',
    templateUrl: './connect.component.html',
    styleUrl: './connect.component.css'
})
export class ConnectComponent {
    constructor(private connectionService: ConnectionService, private authService: AuthService) { }

    doctors: ConnectedUsers[] = [];

    selectedUserId: number = Number(this.authService.getTokenData()?.userId);

    ngOnInit() {
        this.connectionService.viewDoctors(this.selectedUserId).subscribe({
            next: (res) => {
                this.doctors = res.data;
            }
        });
    }

    searchTerm: string = '';
    filteredItems: UserSearch[] = [];


    onSearch() {
        if (this.searchTerm.trim()) {
            this.connectionService.searchDoctors(this.selectedUserId, this.searchTerm).subscribe({next: (res) => {
                this.filteredItems = res.data;
            }})
        } else {
            this.filteredItems = [];
        }
    }

    addConnect(doctorId: number) {
        const request: AddConnection = {
            userId: this.selectedUserId,
            doctorId: doctorId
        }

        this.connectionService.add(request).subscribe();

        this.searchTerm = '';
        this.filteredItems = [];
    }
}
