import { Component } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

interface ChatMessage {
    content: string;
    isUser: boolean;
}
interface ChatRequest {
    message: string;
}

interface ChatResponse {
    response: string;
}
@Component({
    selector: 'app-chatbot',
    template: `
        <div class="chat-widget">
            <div *ngIf="!isOpen" class="chat-button" (click)="toggleChat()">
                <i class="fas fa-comments"></i>
            </div>

            <div *ngIf="isOpen" class="chat-container">
                <div class="chat-header">
                    <span>Chat Assistant</span>
                    <button class="close-button" (click)="toggleChat()">×</button>
                </div>
                <div class="messages">
                    <div *ngFor="let msg of messages"
                         [class.user-message]="msg.isUser"
                         [class.bot-message]="!msg.isUser">
                        {{ msg.content }}
                    </div>
                </div>
                <div class="input-area">
                    <input [(ngModel)]="userInput"
                           (keyup.enter)="sendMessage()"
                           placeholder="Type a message...">
                    <button (click)="sendMessage()">Send</button>
                </div>
            </div>
        </div>
    `,
    styles: [`
        .chat-widget {
            position: fixed;
            bottom: 20px;
            right: 20px;
            z-index: 1000;
        }

        .chat-button {
            width: 60px;
            height: 60px;
            border-radius: 50%;
            background-color: #007bff;
            color: white;
            display: flex;
            align-items: center;
            justify-content: center;
            cursor: pointer;
            box-shadow: 0 2px 5px rgba(0,0,0,0.2);
        }

        .chat-button i {
            font-size: 24px;
        }

        .chat-container {
            position: fixed;
            bottom: 90px;
            right: 20px;
            width: 300px;
            height: 400px;
            border-radius: 10px;
            background: white;
            box-shadow: 0 2px 10px rgba(0,0,0,0.2);
            display: flex;
            flex-direction: column;
        }

        .chat-header {
            padding: 10px;
            background: #007bff;
            color: white;
            border-radius: 10px 10px 0 0;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .close-button {
            background: none;
            border: none;
            color: white;
            font-size: 24px;
            cursor: pointer;
            padding: 0 5px;
        }

        .messages {
            flex: 1;
            overflow-y: auto;
            padding: 10px;
        }

        .user-message, .bot-message {
            margin: 5px;
            padding: 8px;
            border-radius: 5px;
            max-width: 80%;
        }

        .user-message {
            background-color: #e3f2fd;
            margin-left: auto;
        }

        .bot-message {
            background-color: #f5f5f5;
            margin-right: auto;
        }

        .input-area {
            padding: 10px;
            border-top: 1px solid #eee;
            display: flex;
            gap: 5px;
        }

        input {
            flex: 1;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        button {
            padding: 8px 12px;
            background: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background: #0056b3;
        }
    `]
})
export class ChatbotComponent {
    messages: ChatMessage[] = [];
    userInput = '';
    isOpen = false;

    constructor(private http: HttpClient) {}

    toggleChat() {
        this.isOpen = !this.isOpen;
    }

    sendMessage() {
        if (!this.userInput.trim()) return;

        const message = this.userInput.trim();
        this.messages.push({ content: message, isUser: true });
        this.userInput = '';

        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        });

        const request: ChatRequest = { message };

        this.http.post<ChatResponse>('http://localhost:8083/api/chat', request, { headers })
            .subscribe({
                next: (response) => {
                    if (response && response.response) {
                        this.messages.push({ content: response.response, isUser: false });
                    }
                },
                error: (error) => {
                    console.error('Error:', error);
                    this.messages.push({
                        content: 'Sorry, something went wrong. Please try again.',
                        isUser: false
                    });
                }
            });
    }
}
