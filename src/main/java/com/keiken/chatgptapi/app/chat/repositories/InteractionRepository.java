package com.keiken.chatgptapi.app.chat.repositories;

import com.keiken.chatgptapi.app.chat.entities.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InteractionRepository
        extends JpaRepository<Interaction, UUID> {
}

