package com.springcloud.alibaba.rocketmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

/**
 * @author luke
 * @date 2021/1/12 0012 9:08
 * @desc 程序入口
 **/
@EnableBinding(MQApplication.PolledProcessor.class)
@SpringBootApplication
public class MQApplication {

    private final Logger logger = LoggerFactory.getLogger(MQApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MQApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(PollableMessageSource source,
                                    MessageChannel dest) {
        return args -> {
            while (true) {
                boolean result = source.poll(m -> {
                    String payload = (String) m.getPayload();
                    logger.info("Received: " + payload);
                    dest.send(MessageBuilder.withPayload(payload.toUpperCase())
                            .copyHeaders(m.getHeaders())
                            .build());
                }, new ParameterizedTypeReference<String>() {
                });
                if (result) {
                    logger.info("Processed a message");
                } else {
                    logger.info("Nothing to do");
                }
                Thread.sleep(5_000);
            }
        };
    }

    public static interface PolledProcessor {

        @Input
        PollableMessageSource source();

        @Output
        MessageChannel dest();

    }

}
