package com.keiken.chatgptapi.api.openai.prompt.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RequestDTO {

    private String model;
    private List<Message> messages;

    public RequestDTO(String model, String message) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", message));
    }
}
