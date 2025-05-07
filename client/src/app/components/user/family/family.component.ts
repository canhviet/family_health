import { Component } from '@angular/core';
import { FamilyService } from '../../../_services/family.service';
import { DataResponse, UserInFamily, UserSearch } from '../../../../../types';

@Component({
    selector: 'app-family',
    templateUrl: './family.component.html',
    styleUrl: './family.component.css'
})
export class FamilyComponent {
    constructor(private familyService: FamilyService) { }

    users: UserInFamily[] = [];

    ngOnInit() {
        this.familyService.viewFamilyOfUser(1).subscribe({
            next: (res) => {
                this.users = res.data;
            }
        });

    }

    searchTerm: string = '';
    filteredItems: UserSearch[] = [];


    onSearch() {
        if (this.searchTerm.trim()) {
            this.familyService.searchUser(1, this.searchTerm).subscribe({
                next: (res) => {
                    this.filteredItems = res.data;
                    console.log(this.filteredItems);
                }
            });
        } else {
            this.filteredItems = [];
        }
    }
}
