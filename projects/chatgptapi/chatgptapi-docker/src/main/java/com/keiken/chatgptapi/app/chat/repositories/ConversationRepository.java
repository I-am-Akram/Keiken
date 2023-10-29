package com.keiken.chatgptapi.app.chat.repositories;

import com.keiken.chatgptapi.app.chat.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConversationRepository
        extends JpaRepository<Conversation, UUID> {


    Optional<Conversation> findConversationById(UUID id);

}
