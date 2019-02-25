package com.github.feh.wilinando.subscriber.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface CommandEvent {
    String IN_CHANNEL = "command-event";
    String OUT_CHANNEL = "query-event";

    @Input(IN_CHANNEL)
    MessageChannel input();
}
