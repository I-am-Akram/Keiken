package com.keiken.chatgptapi.app.chat.services;

import com.keiken.chatgptapi.app.chat.entities.Interaction;
import com.keiken.chatgptapi.app.chat.repositories.InteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteractionService {

    private final InteractionRepository interactionRepository;

    @Autowired
    public InteractionService(InteractionRepository interactionRepository) {
        this.interactionRepository = interactionRepository;
    }

    public List<Interaction> getInteractions() {
        return interactionRepository.findAll();
    }

}

