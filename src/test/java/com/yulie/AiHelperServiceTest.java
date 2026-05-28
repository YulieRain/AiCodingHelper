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
        String message = aiHelperService.chat("你好");
        System.out.println(message);
    }
}
