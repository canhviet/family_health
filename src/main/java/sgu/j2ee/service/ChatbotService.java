package sgu.j2ee.service;

import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sgu.j2ee.dto.request.ChatRequest;
import sgu.j2ee.dto.response.ChatResponse;
import java.util.Map;
import java.util.List;

@Service
@Slf4j
public class ChatbotService {
    @Value("${spring.ai.groq.api-key}")
    private String apiKey;

    private static final String GROQ_URL = "https://api.groq.com/openai/v1/chat/completions";
    private final RestTemplate restTemplate;

    public ChatbotService() {
        this.restTemplate = new RestTemplate();
    }

    public ChatResponse generateResponse(ChatRequest request) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey.trim());

            var requestBody = Map.of(
                    "model", "llama3-70b-8192",  // Groq's model
                    "messages", List.of(
                            Map.of("role", "user", "content", request.getMessage())
                    ),
                    "temperature", 0.7
            );

            HttpEntity<Map<String, Object>> httpRequest = new HttpEntity<>(requestBody, headers);
            log.info("Sending request to Groq API");

            ResponseEntity<Map> response = restTemplate.postForEntity(GROQ_URL, httpRequest, Map.class);

            if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                log.error("Unauthorized: Invalid API key");
                return new ChatResponse("Error: Invalid API key");
            }

            Map<String, Object> responseBody = response.getBody();
            var choices = (List<Map<String, Object>>) responseBody.get("choices");
            var message = (Map<String, Object>) choices.get(0).get("message");
            String content = (String) message.get("content");

            return new ChatResponse(content.trim());
        } catch (Exception e) {
            log.error("Error calling Groq API: {}", e.getMessage());
            return new ChatResponse("Error: " + e.getMessage());
        }
    }
}