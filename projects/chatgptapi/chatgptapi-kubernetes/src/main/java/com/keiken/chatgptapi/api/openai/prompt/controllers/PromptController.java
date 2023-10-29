package com.keiken.chatgptapi.api.openai.prompt.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keiken.chatgptapi.app.chat.entities.Conversation;
import com.keiken.chatgptapi.app.chat.repositories.ConversationRepository;
import com.keiken.chatgptapi.app.chat.entities.Interaction;
import com.keiken.chatgptapi.app.chat.services.ConversationService;
import com.keiken.chatgptapi.api.openai.prompt.entities.Prompt;
import com.keiken.chatgptapi.api.openai.prompt.services.PromptService;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class PromptController {

    private final PromptService promptService;
    private final ConversationService conversationService;
    @Value("${openai.api.key}")
    private String OPENAI_API_KEY;

    @Autowired
    private ConversationRepository repository;

    @Autowired
    public PromptController(PromptService promptService, ConversationService conversationService) {
        this.promptService = promptService;
        this.conversationService = conversationService;
    }

    @PostMapping(path = "api/prompts", consumes = "application/json", produces = "application/json")
    public String handlePrompt(@RequestBody Prompt prompt) throws IOException, ParseException, InterruptedException, JSONException {
        String response = promptService.handlePrompt(prompt);
        return response;
    }

    private void createAndSaveNewConversation(String message, String response) {
        repository.save(
            new Conversation(
                LocalDateTime.now(),
                List.of(new Interaction(
                    message,
                    response
                ))
            )
        );
    }

    private void fetchAndSaveExistingConversation(String conversation_id, String message, String response) {
        UUID id = UUID.fromString(conversation_id);
        Conversation conversation = conversationService.fetchConversationById(id);
        conversation.addInteraction(
            new Interaction(
                message,
                response
            ));
        repository.save(conversation);
    }


    @PostMapping(path = "chat/send")
    public String sendResponse(@RequestBody String body) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(body);

            String message = jsonNode.get("message").asText();
            String conversation_id = jsonNode.get("conversation_id").asText();

            Prompt prompt = new Prompt(OPENAI_API_KEY, message);
            String response = promptService.handlePrompt(prompt);

//            String response = "This is a response for " + message;

            if (conversation_id.equals("new")) {
                createAndSaveNewConversation(message, response);
            } else {
                fetchAndSaveExistingConversation(conversation_id, message, response);
            }

            return response;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Invalid request body";
        }
    }


}

