package org.example.chatgptbot.controller;

import org.example.chatgbtbot.dto.ChatBotRequest;
import org.example.chatgbtbot.dto.ChatBotResponse;
import org.example.chatgbtbot.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatBotController {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.url}")
    private String apiUrl;

    @Autowired
    private RestTemplate template;

    @GetMapping
    public ResponseEntity<Message> chat(@RequestParam String prompt)
    {
        ChatBotRequest chatBotRequest = new ChatBotRequest(model, prompt);
        return new ResponseEntity<>(
                Objects.requireNonNull(template.postForObject(apiUrl, chatBotRequest, ChatBotResponse.class)).getChoice().get(0).getMessage(),
                HttpStatus.OK
        );
    }
}
