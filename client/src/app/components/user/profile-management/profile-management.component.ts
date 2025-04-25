import { Component } from '@angular/core';
import {ViewportScroller} from "@angular/common";

@Component({
  selector: 'app-profile-management',
  templateUrl: './profile-management.component.html',
  styleUrl: './profile-management.component.css'
})
export class ProfileManagementComponent {
    constructor(private viewportScroller: ViewportScroller) {}

    scrollToSection(fragment: string): void {
        this.viewportScroller.scrollToAnchor(fragment);
    }
}
