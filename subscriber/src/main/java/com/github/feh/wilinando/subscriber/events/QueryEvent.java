package com.github.feh.wilinando.subscriber.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface QueryEvent {
    String CHANNEL = "query-event";

    @Input(CHANNEL)
    MessageChannel channel();
}
