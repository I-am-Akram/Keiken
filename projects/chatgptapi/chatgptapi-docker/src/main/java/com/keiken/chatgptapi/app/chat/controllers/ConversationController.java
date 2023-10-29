package com.keiken.chatgptapi.app.chat.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keiken.chatgptapi.app.chat.entities.Conversation;
import com.keiken.chatgptapi.app.chat.repositories.ConversationRepository;
import com.keiken.chatgptapi.app.chat.services.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Controller
public class ConversationController {

    private final ConversationService conversationService;

    @Autowired
    private ConversationRepository repository;

    @Autowired
    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

//    @RequestMapping(path = "api/conversations", method = RequestMethod.GET)
    @GetMapping(path = "api/conversations")
    public String fetchConversations(Model model) {
        try {
            model.addAttribute("result", conversationService.getConversations().toString());
            return "api/result";
        } catch (Exception e) {
            return "error/error";
        }

    }

    @GetMapping("api/conversations/{conversation_id}")
    public String fetchConversationById(Model model, @PathVariable("conversation_id") UUID id) throws NoSuchElementException {
        try {
            model.addAttribute("result", conversationService.fetchConversationById(id).toString());
            return "api/result";
        } catch (Exception e) {
            return "error/error";
        }
    }

    @GetMapping(path = {"/conversations", "/chat"})
    public String getConversations(Model model) {
        List<Conversation> conversations = conversationService.getConversations();
        model.addAttribute("conversations", conversations);
        return "app/chat/conversations";
    }

    @GetMapping("chat/{conversation_id}")
    public String getConversationById(Model model, @PathVariable("conversation_id") UUID id) throws NoSuchElementException {
        try {
            model.addAttribute("conversation_id", id);
            model.addAttribute("conversation", conversationService.fetchConversationById(id));
            return "app/chat/chat";
        } catch (Exception e) {
            return "error/error";
        }
    }

    @GetMapping("chat/new")
    public String getNewConversation(Model model) {
        model.addAttribute("conversation_id", "new");
        model.addAttribute("conversation", new Conversation());
        return "app/chat/chat";
    }

    @PostMapping(path = "chat/delete")
    public String deleteConversation(@RequestBody String body) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(body);
            String conversation_id = jsonNode.get("conversation_id").asText();
            UUID id = UUID.fromString(conversation_id);
            repository.deleteById(id);
            return "app/chat/conversations";
        } catch (Exception e) {
            e.printStackTrace();
            return "error/error";
        }
    }

}
