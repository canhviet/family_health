import { Component } from '@angular/core';
import { DocumentService } from '../../../_services/document.service';
import { Document } from '../../../../../types';
import { UploadComponent } from '../../upload/upload.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
    selector: 'app-document',
    templateUrl: './document.component.html',
    styleUrl: './document.component.css'
})
export class DocumentComponent {
    constructor(private documentService: DocumentService, private dialog: MatDialog) { }

    documents: Document[] = [];

    ngOnInit() {

        this.documentService.getByUser(1).subscribe({
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
