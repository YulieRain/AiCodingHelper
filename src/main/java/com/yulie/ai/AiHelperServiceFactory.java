package com.yulie.ai;


import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yulie
 * @version 1.0
 * @description:
 */

@Configuration
public class AiHelperServiceFactory {

    @Autowired
    private ChatModel qwenChatModel;

    @Bean
    public AiHelperService createAiHelperService(AiCodeHelper aiCodeHelper) {
        MessageWindowChatMemory maxMessages = MessageWindowChatMemory.withMaxMessages(10);

        AiHelperService aiHelperService = AiServices.builder(AiHelperService.class)
                .chatMemory(maxMessages)
                .chatModel(qwenChatModel)
                .build();

        return aiHelperService;
    }
}
