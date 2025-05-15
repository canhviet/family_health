import { Component } from '@angular/core';
import { DocumentService } from '../../../_services/document.service';
import { Document } from '../../../../../types';
import { UploadComponent } from '../../upload/upload.component';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from '../../../_services/auth.service';

@Component({
    selector: 'app-document',
    templateUrl: './document.component.html',
    styleUrl: './document.component.css'
})
export class DocumentComponent {
    constructor(private documentService: DocumentService, private dialog: MatDialog , private authService: AuthService) { }

    documents: Document[] = [];

    ngOnInit() {

        this.documentService.getByUser(Number(this.authService.getTokenData()?.userId)).subscribe({
            next: (res) => {
                this.documents = res.data;
            }
        })
    }

    openDocument(url: string): void {
        if (url) {
            window.open(url, '_blank');
        }
    }

    onUpload(): void {
        this.dialog.open(UploadComponent, {
            width: '500px'
        });
    }
}
