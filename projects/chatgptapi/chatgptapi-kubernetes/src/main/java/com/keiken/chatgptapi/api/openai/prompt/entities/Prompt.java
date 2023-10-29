package com.keiken.chatgptapi.api.openai.prompt.entities;

public class Prompt {

    private String openaiToken;
    private String question;

    public Prompt() {
    }

    public Prompt(String openaiToken, String question) {
        this.openaiToken = openaiToken;
        this.question = question;
    }

    public String getOpenaiToken() {
        return openaiToken;
    }

    public void setOpenaiToken(String openaiToken) {
        this.openaiToken = openaiToken;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Prompt{" +
                "openaiToken='" + openaiToken + '\'' +
                ", question='" + question + '\'' +
                '}';
    }
}
