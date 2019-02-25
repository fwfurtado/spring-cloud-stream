package com.github.feh.wilinando.subscriber.models;

import java.util.UUID;

public interface Command<T> {
    UUID getCorrelationId();
}
