package com.yulie;


import com.yulie.ai.AiHelperService;
import dev.langchain4j.service.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yulie
 * @version 1.0
 * @description:
 */

@SpringBootTest
public class AiHelperServiceTest {

    @Autowired
    private AiHelperService aiHelperService;

    @Test
    void chat() {
        String message = aiHelperService.chat("你好,我是yulie");
        System.out.println(message);
        message = aiHelperService.chat("你好,我是谁？");
        System.out.println(message);
    }

    // 结构化输出respon回复
    @Test
    void chatReportReport(){
        String usremessage = "你好，给我几条学习建议";
        AiHelperService.Report chatwithReport = aiHelperService.chatwithReport(usremessage);
        System.out.println(chatwithReport);
    }

    @Test
    void chatwithRag() {
        String usermeassage = "请给我学习编程的方法";
        Result<String> stringResult = aiHelperService.chatwithRag(usermeassage);
        System.out.println(stringResult.content());
        System.out.println(stringResult.sources());
    }


    @Test
    void chatwithTools(){
        String chat = aiHelperService.chat("有哪些常见的计算机网络面试题");
        System.out.println(chat);
    }


    @Test
    void chatwithMcp(){
        String chat = aiHelperService.chat("原神的官网是什么");
        System.out.println(chat);
    }

    @Test
    void chatwithGuardrail(){
        String chat = aiHelperService.chat("你好");
        System.out.println(chat);
    }
}
