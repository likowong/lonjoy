package com.lonjoy.auth.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lonjoy.auth.provider.mq.model.TestMq;
import com.lonjoy.auth.provider.mq.provider.MqProvider;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LonJoyAuthApplicationTests {

    @Test
    public void testSendMqMsg() throws JsonProcessingException {
        MqProvider.sendTestMsg(new TestMq("test rabbitmq"));
    }

}
