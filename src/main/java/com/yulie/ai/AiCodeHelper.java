package com.yulie.ai;


import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author yulie
 * @version 1.0
 * @description:
 */

@Service
@Slf4j
public class AiCodeHelper {


    @Resource
    private ChatModel qwenChatModel;
    public String chat(String message) {
        UserMessage userMessage = UserMessage.from(message);
        ChatResponse chat = qwenChatModel.chat(userMessage);
        AiMessage aiMessage = chat.aiMessage();
        log.info("ai输出: {}", aiMessage.text());
        return aiMessage.text();
    }
}
