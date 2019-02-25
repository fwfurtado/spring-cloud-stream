package com.github.feh.wilinando.subscriber.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface LogEvent {

    String IN_CHANNEL = "log-event";

    @Input(IN_CHANNEL)
    MessageChannel channel();
}
