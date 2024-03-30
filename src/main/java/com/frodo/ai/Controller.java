package com.frodo.ai;

import org.springframework.ai.chat.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    final ChatClient chatClient;

    public Controller(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/q")
    public String query(@RequestParam String question ) {
        return chatClient.call(question);
    }
}
