package com.frodo.ai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(PromptController.class);

    final ChatClient chatClient;

    public Controller(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/q")
    public String query(@RequestParam String question ) {
        logger.info("** Question: {}", question);
        String answer = chatClient.call(question);
        logger.info("** Answer: {}", answer);
        return answer;
    }
}
