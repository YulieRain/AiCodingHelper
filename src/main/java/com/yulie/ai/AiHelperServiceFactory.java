package com.yulie.ai;


import com.yulie.ai.tools.InterviewQuestionTool;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
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

    @Resource
    private ChatModel myQwenChatModel;

    @Resource
    private ContentRetriever contentRetriever;

    @Resource
    private McpToolProvider mcpToolProvider;
    @Bean
    public AiHelperService createAiHelperService() {
        MessageWindowChatMemory maxMessages = MessageWindowChatMemory.withMaxMessages(10);

        AiHelperService aiHelperService = AiServices.builder(AiHelperService.class)
                .chatMemory(maxMessages)
                .chatModel(myQwenChatModel)
                .contentRetriever(contentRetriever) // 添加RAG检索
                .tools(new InterviewQuestionTool()) // 添加工具调用
                .toolProvider(mcpToolProvider) // 添加MCP工具
                .build();

        return aiHelperService;
    }
}
