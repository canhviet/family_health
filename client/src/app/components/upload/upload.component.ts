import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UploadService } from '../../_services/upload.service';
import { Document } from '../../../../types';
import { DocumentService } from '../../_services/document.service';
import { AuthService } from '../../_services/auth.service';

@Component({
    selector: 'app-upload',
    templateUrl: './upload.component.html',
    styleUrls: ['./upload.component.css']
})
export class UploadComponent {

    constructor(
        private uploadService: UploadService,
        public dialogRef: MatDialogRef<UploadComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any,
        private documentService: DocumentService,
        private authService: AuthService
    ) { }

    ngOnInit() {
        if(this.data) {
            this.document.userId = Number(this.data);
        } else {
            this.document.userId = Number(this.authService.getTokenData()?.userId);
        }
    }

    selectedFile: File | null = null;
    previewUrl: string | null = null;
    uploading = false;
    errorMessage: string | null = null;

    document: Document = {
        documentName: '',
        documentUrl: '',
        uploadDate: new Date,
        userId: 0
    };

    onFileSelected(event: Event): void {
        const input = event.target as HTMLInputElement;
        if (input.files && input.files.length > 0) {
            this.selectedFile = input.files[0];
            if (this.selectedFile.type.startsWith('image/')) {
                const reader = new FileReader();
                reader.onload = () => {
                    this.previewUrl = reader.result as string;
                };
                reader.readAsDataURL(this.selectedFile);
            } else {
                this.previewUrl = null;
            }
        }
    }

    uploadFile(): void {
        if (this.selectedFile) {
            this.uploading = true;
            this.uploadService.uploadFile(this.selectedFile).subscribe({
                next: (response) => {
                    this.uploading = false;
                    this.dialogRef.close({ url: response.url });

                    this.document.documentUrl = response.url;

                    this.documentService.addDocument(this.document).subscribe();
                },
                error: (error) => {
                    this.uploading = false;
                    this.errorMessage = 'Upload failed: ' + error.message;
                }
            });
        }
    }

    cancel(): void {
        this.dialogRef.close();
    }
}