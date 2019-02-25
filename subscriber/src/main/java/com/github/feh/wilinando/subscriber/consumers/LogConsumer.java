package com.github.feh.wilinando.subscriber.consumers;

import com.github.feh.wilinando.subscriber.events.LogEvent;
import com.github.feh.wilinando.subscriber.models.Log;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

@EnableBinding(LogEvent.class)
public class LogConsumer {

    @StreamListener(target = LogEvent.IN_CHANNEL, condition = "headers['level'] == 'INFO' ")
    public void info(Log log) {
        System.out.println("[INFO] " + log);
    }

    @StreamListener(target = LogEvent.IN_CHANNEL, condition = "headers['level'] == 'WARNING' ")
    public void warning(Log log){
        System.out.println("[WARNING] " + log);
    }

    @StreamListener(target = LogEvent.IN_CHANNEL, condition = "headers['level'] == 'ERROR' ")
    public void error(Log log){
        System.out.println("[ERROR] " + log);
    }

    @StreamListener(LogEvent.IN_CHANNEL)
    public void all(Message<Log> log){
        System.out.println("[ALL] " + log);
    }

}
