package com.yulie.ai;


import dev.langchain4j.service.SystemMessage;

public interface AiHelperService {

    @SystemMessage(fromResource = "System_Prompt.txt") // 添加系统预设
    String chat(String message); //  LangChain4j 在运行时动态创建接口实现
}
