package com.github.fehwilinando.publisher.producer;

import com.github.fehwilinando.publisher.events.LogEvent;
import com.github.fehwilinando.publisher.models.Log;
import com.github.fehwilinando.publisher.models.LogLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;

@EnableBinding(LogEvent.class)
public class LogProducer {

    private static AtomicInteger counter = new AtomicInteger();
    private Random random = new Random();

    @Autowired
    private LogEvent event;

    public void send() {
        MessageChannel sender = event.channel();

        sender.send(MessageBuilder.withPayload("BLABLABLA").build());
    }

    @Bean
    @InboundChannelAdapter(channel = LogEvent.CHANNEL)
    public MessageSource<Log> produces() {
        return () -> {
            String content = format("Produces a new log: %s", counter.getAndIncrement());

            int levelIndex = random.nextInt(LogLevel.values().length);

            LogLevel level = LogLevel.values()[levelIndex];

            return MessageBuilder
                    .withPayload(new Log(level, content))
                        .setHeaderIfAbsent("level", level)
                            .build();
        };
    }
}
