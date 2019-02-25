package com.github.fehwilinando.publisher.events;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface LogEvent {

    String CHANNEL = "log-event";

    @Output(LogEvent.CHANNEL)
    MessageChannel channel();
}
