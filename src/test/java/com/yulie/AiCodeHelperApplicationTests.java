package com.yulie;

import com.yulie.ai.AiCodeHelper;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import jakarta.annotation.Resource;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AiCodeHelperApplicationTests {

    @Resource
    private AiCodeHelper aiCodeHelper;

    @Test
    void chat() {
        aiCodeHelper.chat("你是谁？");
    }

    @Test
    void chatwithMessage() {
        UserMessage userMessage = UserMessage.from(
                TextContent.from("这是什么"),
                ImageContent.from("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png")
        );
        aiCodeHelper.chatwithMessage(userMessage);
    }

    @Test
    void chatAddSystemMessage() {
        aiCodeHelper.chatAddSystemMessage("你好");
    }
}
