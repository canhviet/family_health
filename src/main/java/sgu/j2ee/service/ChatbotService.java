package sgu.j2ee.service;

import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sgu.j2ee.dto.request.ChatRequest;
import sgu.j2ee.dto.response.ChatResponse;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ChatbotService {
    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    private static final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";
    private final RestTemplate restTemplate;

    public ChatbotService() {
        this.restTemplate = new RestTemplate();
    }

    @PostConstruct
    private void validateConfig() {
        if (apiKey == null || apiKey.isEmpty() || apiKey.equals("${spring.ai.openai.api-key}")) {
            log.error("OpenAI API key is not configured!");
        } else {
            log.info("OpenAI API key is configured");
        }
    }

    public ChatResponse generateResponse(ChatRequest request) {
        if (apiKey == null || apiKey.isEmpty() || apiKey.equals("${spring.ai.openai.api-key}")) {
            return new ChatResponse("API key not configured. Please check your application.properties file.");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", Arrays.asList(
                Map.of("role", "user", "content", request.getMessage())
        ));
        requestBody.put("temperature", 0.7);

        HttpEntity<Map<String, Object>> httpRequest = new HttpEntity<>(requestBody, headers);

        try {
            log.info("Sending request to OpenAI API");
            ResponseEntity<Map> response = restTemplate.postForEntity(OPENAI_URL, httpRequest, Map.class);

            Map<String, Object> responseBody = response.getBody();
            if (responseBody == null) {
                log.error("Received null response body from OpenAI API");
                return new ChatResponse("Error: Received null response from OpenAI API");
            }

            List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
            Map<String, Object> message_response = (Map<String, Object>) choices.get(0).get("message");
            String content = (String) message_response.get("content");

            return new ChatResponse(content.trim());
        } catch (Exception e) {
            log.error("Error calling OpenAI API: {}", e.getMessage());
            return new ChatResponse("Error: " + e.getMessage());
        }
    }
}