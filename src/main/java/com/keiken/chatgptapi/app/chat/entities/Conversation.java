package com.keiken.chatgptapi.app.chat.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "t_conversation")
public class Conversation {
    @Id
    @GeneratedValue
    private UUID id;

    private LocalDateTime creationTime;

    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "fk_conversation_id")
    private List<Interaction> interactions;


    public Conversation() {
    }

    public Conversation(UUID id, LocalDateTime creationTime, List<Interaction> interactions) {
        this.id = id;
        this.creationTime = creationTime;
        this.interactions = interactions;
    }

    public Conversation(LocalDateTime creationTime, List<Interaction> interactions) {
        this.creationTime = creationTime;
        this.interactions = interactions;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public List<Interaction> getInteractions() {
        return interactions;
    }

    public void setInteractions(List<Interaction> interactions) {
        this.interactions = interactions;
    }

    public void addInteraction(Interaction interaction) {
        if (interactions == null) {
            interactions = new ArrayList<>();
        }
        interactions.add(interaction);
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "id=" + id +
                ", creationTime=" + creationTime +
                ", interactions=" + interactions +
                '}';
    }
}

