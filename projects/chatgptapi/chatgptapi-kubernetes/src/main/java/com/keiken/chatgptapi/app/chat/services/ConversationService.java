package com.keiken.chatgptapi.app.chat.services;

import com.keiken.chatgptapi.app.chat.entities.Conversation;
import com.keiken.chatgptapi.app.chat.repositories.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;

    @Autowired
    public ConversationService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    public List<Conversation> getConversations() {
        return conversationRepository.findAll();
    }

    public Conversation fetchConversationById(UUID id) throws NoSuchElementException {
        Optional<Conversation> conversationOptional =
                conversationRepository.findConversationById(id);

        Conversation conversationById = conversationOptional.orElseThrow(() ->
                new NoSuchElementException("No conversation with the given id was found"));

        return conversationById;
    }
}
