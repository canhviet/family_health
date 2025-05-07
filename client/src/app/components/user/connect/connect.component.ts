import { Component } from '@angular/core';
import { ConnectionService } from '../../../_services/connection.service';
import { ConnectedDoctors } from '../../../../../types';

@Component({
    selector: 'app-connect',
    templateUrl: './connect.component.html',
    styleUrl: './connect.component.css'
})
export class ConnectComponent {
    constructor(private connectionService: ConnectionService) { }

    doctors: ConnectedDoctors[] = [];

    ngOnInit() {
        this.connectionService.viewDoctors(3).subscribe({
            next: (res) => {
                this.doctors = res.data;
            }
        });
    }

    searchTerm: string = '';
    filteredItems: string[] = [];


    onSearch() {
        if (this.searchTerm.trim()) {

        } else {
            this.filteredItems = [];
        }
    }
}
