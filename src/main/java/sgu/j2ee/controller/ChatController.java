package sgu.j2ee.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sgu.j2ee.dto.request.ChatRequest;
import sgu.j2ee.dto.response.ChatResponse;
import sgu.j2ee.service.ChatbotService;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:4200")
public class ChatController {

    private final ChatbotService chatbotService;

    public ChatController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request) {
        ChatResponse response = chatbotService.generateResponse(request);
        return ResponseEntity.ok(response);
    }
}