package com.github.fehwilinando.publisher.models;

import java.time.ZonedDateTime;
import java.util.StringJoiner;

public class Log {
    private ZonedDateTime date;
    private LogLevel level;
    private String content;

    private Log(){}

    public Log(LogLevel level, String content) {
        this.date = ZonedDateTime.now();
        this.level = level;
        this.content = content;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public LogLevel getLevel() {
        return level;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Log.class.getSimpleName() + "[", "]")
                .add("date=" + date)
                .add("level=" + level)
                .add("content='" + content + "'")
                .toString();
    }
}
