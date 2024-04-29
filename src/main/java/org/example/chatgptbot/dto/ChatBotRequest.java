package org.example.chatgptbot.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatBotRequest {

    private String model;
    private List<Message> messages;

    public ChatBotRequest(String model, String prompt)
    {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
    }
}
