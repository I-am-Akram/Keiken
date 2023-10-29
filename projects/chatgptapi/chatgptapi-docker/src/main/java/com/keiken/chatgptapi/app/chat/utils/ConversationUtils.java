package com.keiken.chatgptapi.app.chat.utils;

import com.keiken.chatgptapi.app.chat.entities.Conversation;
import com.keiken.chatgptapi.app.chat.entities.Interaction;
import com.keiken.chatgptapi.app.chat.repositories.ConversationRepository;
import com.keiken.chatgptapi.app.chat.services.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ConversationUtils {

    private final ConversationService conversationService;

    @Autowired
    private ConversationRepository repository;

    @Autowired
    public ConversationUtils(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    public void createAndSaveNewConversation(String message, String response) {
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

    public void fetchAndSaveExistingConversation(String conversation_id, String message, String response) {
        UUID id = UUID.fromString(conversation_id);
        Conversation conversation = conversationService.fetchConversationById(id);
        conversation.addInteraction(
            new Interaction(
                    message,
                    response
            ));
        repository.save(conversation);
    }

}
