package com.frodo.ai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PromptController {

    private static final Logger logger = LoggerFactory.getLogger(PromptController.class);

    private final ChatClient chatClient;

    @Value("classpath:context-prompt.st")
    private Resource prompt;
    @Value("classpath:context.txt")
    private Resource context;

    public PromptController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/q2")
    public String query( @RequestParam(value = "question", defaultValue = "What is quote of the day ?") String question) {
        logger.info("** Question: {}", question);

        PromptTemplate promptTemplate = new PromptTemplate(prompt);

        Map<String,Object> map  = new HashMap<>();

        map.put("question", question);
        map.put("context", context);

        Prompt prompt = promptTemplate.create(map);
        ChatResponse response = chatClient.call(prompt);

        String answer = response.getResult().getOutput().getContent();
        logger.info("** Answer: {}", answer);
        return answer;
    }
}
