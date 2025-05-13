import { Component } from '@angular/core';
import { ConnectionService } from '../../../_services/connection.service';
import { AddConnection, ConnectedUsers, UserSearch } from '../../../../../types';

@Component({
    selector: 'app-connect',
    templateUrl: './connect.component.html',
    styleUrl: './connect.component.css'
})
export class ConnectComponent {
    constructor(private connectionService: ConnectionService) { }

    doctors: ConnectedUsers[] = [];

    ngOnInit() {
        this.connectionService.viewDoctors(1).subscribe({
            next: (res) => {
                this.doctors = res.data;
            }
        });
    }

    searchTerm: string = '';
    filteredItems: UserSearch[] = [];


    onSearch() {
        if (this.searchTerm.trim()) {
            this.connectionService.searchDoctors(1, this.searchTerm).subscribe({next: (res) => {
                this.filteredItems = res.data;
            }})
        } else {
            this.filteredItems = [];
        }
    }

    addConnect(doctorId: number) {
        const request: AddConnection = {
            userId: 1,
            doctorId: doctorId
        }

        this.connectionService.add(request).subscribe({next: () => {
            this.searchTerm = '';
            this.filteredItems = [];
        }});
    }
}
