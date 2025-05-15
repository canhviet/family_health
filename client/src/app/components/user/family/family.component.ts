import { Component } from '@angular/core';
import { FamilyService } from '../../../_services/family.service';
import { AddNewMember, Family, UserInFamily, UserResponse, UserSearch } from '../../../../../types';
import { UserService } from '../../../_services/user.service';
import { MatDialog } from '@angular/material/dialog';
import { AddMemberComponent } from './add-member/add-member.component';
import { first } from 'rxjs';
import { AuthService } from '../../../_services/auth.service';
import { ViewUserRecordComponent } from '../../view-user-record/view-user-record.component';

@Component({
    selector: 'app-family',
    templateUrl: './family.component.html',
    styleUrl: './family.component.css'
})
export class FamilyComponent {
    constructor(private familyService: FamilyService, private userService: UserService,
        private dialog: MatDialog, private authService: AuthService
    ) { }

    users: UserInFamily[] = [];

    userIsHead: boolean = false;

    notInFamily: boolean = true;

    selectedUserId: number = Number(this.authService.getTokenData()?.userId);

    family: Family = {
        headId: 0,
        familyId: 0,
    };

    user: UserResponse = {
        userId: 0,
        username: '',
        email: '',
        firstName: '',
        lastName: '',
        dob: new Date,
        gender: '',
        address: '',
        phone: '',
        healthInsuranceCode: '',
        familyId: 0,
        cityzenId: ''
    };

    ngOnInit() {
        this.userService.viewById(this.selectedUserId).subscribe({
            next: (res) => {
                this.user = res.data;

                if (this.user.familyId != -1) {

                    this.notInFamily = false;

                    this.familyService.viewFamilyOfUser(this.user.familyId).subscribe({
                        next: (res) => {
                            this.users = res.data;
                        }
                    });

                    this.familyService.viewById(this.user.familyId).subscribe({
                        next: (res) => {
                            this.family = res.data;

                            if (this.family.headId == this.user.userId) {
                                this.userIsHead = true;
                            }
                        }
                    })
                }
            }
        });
    }

    searchTerm: string = '';
    filteredItems: UserSearch[] = [];


    onSearch() {
        if (this.searchTerm.trim()) {
            this.familyService.searchUser(this.selectedUserId, this.searchTerm).subscribe({
                next: (res) => {
                    this.filteredItems = res.data;
                }
            });
        } else {
            this.filteredItems = [];
        }
    }

    addNewFamily() {
        const f: Family = {
            headId: this.user.userId
        }

        this.familyService.addNewFamily(f).subscribe(() => {
            this.notInFamily = false;
        });
    }

    addToFamily(userId: number) {

        const data = {
            headId: this.user.userId,
            userId:userId
        }

        this.dialog.open(AddMemberComponent, {
            width: '500px', data: data
        });

        this.searchTerm = '';
        this.filteredItems = [];
    }

    openRecords(userId: number) {
        this.dialog.open(ViewUserRecordComponent, {
            width: '90vw',
            height: 'auto',
            data: userId
        });
    }
}
