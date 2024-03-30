package com.frodo.ai;

import org.springframework.ai.chat.ChatClient;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiApplication.class, args);
	}

	@Bean
	ApplicationRunner demo(ChatClient chatClient) {
		return args -> {

			String prompt="hello, world, can yopu give me todays quote";
			var response = chatClient.call(prompt);
			System.out.println(" **** quote of the day: "+ response);

		};
	}
}
