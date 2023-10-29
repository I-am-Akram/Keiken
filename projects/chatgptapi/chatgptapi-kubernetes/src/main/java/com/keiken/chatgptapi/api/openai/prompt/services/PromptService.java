package com.keiken.chatgptapi.api.openai.prompt.services;

import com.keiken.chatgptapi.api.openai.prompt.dto.RequestDTO;
import com.keiken.chatgptapi.api.openai.prompt.dto.ResponseDTO;
import com.keiken.chatgptapi.api.openai.prompt.entities.Prompt;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class PromptService {

    @Value("${openai.api.url}")
    private String OPENAI_API_URL;

    @Value("${openai.api.model}")
    private String OPENAI_API_MODEL;

    @Autowired
    private RestTemplate template;

    public PromptService() {
    }

    public String generateChatResponse(String openaiToken, String question) throws IOException, InterruptedException, ParseException, JSONException {

        RequestDTO requestDTO = new RequestDTO(OPENAI_API_MODEL, question);
        ResponseDTO responseDTO = template.postForObject(OPENAI_API_URL, requestDTO, ResponseDTO.class);
        return responseDTO.getChoices().get(0).getMessage().getContent();

    }

    public String handlePrompt(Prompt prompt) throws IOException, ParseException, InterruptedException, JSONException {
        String openaiToken = prompt.getOpenaiToken();
        String question = prompt.getQuestion();

        String response = generateChatResponse(openaiToken, question);
        return response;
    }
}
