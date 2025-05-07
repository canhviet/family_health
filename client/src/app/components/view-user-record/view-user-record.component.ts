import { Component } from '@angular/core';

@Component({
    selector: 'app-view-user-record',
    templateUrl: './view-user-record.component.html',
    styleUrl: './view-user-record.component.css'
})
export class ViewUserRecordComponent {
    selectedTabIndex = 0;

    onTabChange(index: number) {
        this.selectedTabIndex = index;
    }
}
