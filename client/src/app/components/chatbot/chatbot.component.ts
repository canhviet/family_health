import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
    selector: 'app-chatbot',
    templateUrl: './chatbot.component.html',
    styleUrls: ['./chatbot.component.css']
})
export class ChatbotComponent {
    message: string = '';
    response: string = '';
    isOpen: boolean = false;

    constructor(private http: HttpClient) {}

    toggleChat(): void {
        this.isOpen = !this.isOpen;
    }

    sendMessage(): void {
        if (!this.message.trim()) return;

        this.http.post('/api/chat', this.message, { responseType: 'text' }).subscribe(
            res => this.response = res,
            err => this.response = 'Có lỗi xảy ra!'
        );
        this.message = '';
    }
}
