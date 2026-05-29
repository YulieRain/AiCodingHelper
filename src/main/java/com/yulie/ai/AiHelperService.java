package com.yulie.ai;


import com.yulie.ai.guardrail.SafeInputGuardrail;
import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.guardrail.InputGuardrails;

import java.util.List;


@InputGuardrails( { SafeInputGuardrail.class} )
public interface AiHelperService {

    @SystemMessage(fromResource = "System_Prompt.txt") // 添加系统预设
    String chat(String message); //  LangChain4j 在运行时动态创建接口实现


    @SystemMessage(fromResource = "System_Prompt.txt") // 添加系统预设
    Report chatwithReport(String message); //  LangChain4j 在运行时动态创建接口实现

    // 定义大模型返回值json
    record Report(String name, List<String> items){}

    @SystemMessage(fromResource = "System_Prompt.txt")
    Result<String> chatwithRag(String message);
}
