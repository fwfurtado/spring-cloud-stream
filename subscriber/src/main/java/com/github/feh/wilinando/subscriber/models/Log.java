package com.github.feh.wilinando.subscriber.models;

import java.time.ZonedDateTime;
import java.util.StringJoiner;

public class Log {
    private ZonedDateTime date;
    private LogLevel level;
    private String content;

    private Log() {
    }

    private Log(LogLevel level, String content) {
        this.level = level;
        this.content = content;
        this.date = ZonedDateTime.now();
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

    public static Log info(String content){
        return log(LogLevel.INFO, content);
    }
    public static Log warning(String content){
        return log(LogLevel.WARNING, content);
    }
    public static Log error(String content){
        return log(LogLevel.ERROR, content);
    }

    private static Log log(LogLevel level, String content) {
        return new Log(level, content);
    }

}
