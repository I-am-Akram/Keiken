package com.keiken.chatgptapi.app.chat.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "t_interaction")
public class Interaction {
    @Id
    @GeneratedValue
    private UUID id;

//    @Column(nullable = false)
    private String question;

    private String response;

    public Interaction() {
    }

    public Interaction(UUID id, String question, String response) {
        this.id = id;
        this.question = question;
        this.response = response;
    }

    public Interaction(String question, String response) {
        this.question = question;
        this.response = response;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Interaction{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}

