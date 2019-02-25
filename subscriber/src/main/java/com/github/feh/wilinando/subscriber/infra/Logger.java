package com.github.feh.wilinando.subscriber.infra;

import com.github.feh.wilinando.subscriber.events.LogEvent;
import com.github.feh.wilinando.subscriber.models.Log;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class Logger<T> {
    private final MessageChannel event;

    private static final String LOG_FORMAT = "%s [%s] [%s] %s";

    public Logger(LogEvent logEvent) {
        this.event = logEvent.channel();
    }

    private String formatContent(String formatter, Object... args) {
        return String.format(formatter, args);
    }

    private void push(Log log) {
        event.send(buildMessageFrom(log));

        System.out.println(log);
        System.out.println(String.format(LOG_FORMAT, log.getDate(), log.getLevel(), getGenericClassName(), log.getContent()));

    }

    private String getGenericClassName() {
//        ParameterizedType type = (ParameterizedType) getClass();
        return null;
    }

    public void info(String formatter, Object... args){
        String content = formatContent(formatter, args);

        Log log = Log.info(content);

        push(log);

    }

    public void warning(String formatter, Object... args){
        String content = formatContent(formatter, args);

        Log log = Log.warning(content);

        push(log);
    }

    public void error(String formatter, Object... args){
        String content = formatContent(formatter, args);

        Log log = Log.error(content);

        push(log);
    }


    private Message<Log> buildMessageFrom(Log log) {
        return MessageBuilder
                        .withPayload(log)
                            .setHeaderIfAbsent("level", log.getLevel()).build();
    }
}
