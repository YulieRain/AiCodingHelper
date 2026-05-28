package com.yulie;


import com.yulie.ai.AiHelperService;
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


    @Test
    void chatReportReport(){
        String usremessage = "你好，给我几条学习建议";
        AiHelperService.Report chatwithReport = aiHelperService.chatwithReport(usremessage);
        System.out.println(chatwithReport);
    }
}
