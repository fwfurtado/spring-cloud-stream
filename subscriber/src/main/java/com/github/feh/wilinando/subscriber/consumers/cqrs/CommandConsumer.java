package com.github.feh.wilinando.subscriber.consumers.cqrs;

import com.github.feh.wilinando.subscriber.repositories.AnswerCommandRepository;
import com.github.feh.wilinando.subscriber.events.CommandEvent;
import com.github.feh.wilinando.subscriber.models.AnswerCommand;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(CommandEvent.class)
public class CommandConsumer {

    private final AnswerCommandRepository repository;

    public CommandConsumer(AnswerCommandRepository repository) {
        this.repository = repository;
    }

    @StreamListener(CommandEvent.IN_CHANNEL)
    @SendTo(CommandEvent.OUT_CHANNEL)
    public AnswerCommand saveCommand(AnswerCommand command){
        repository.save(command);

        return command;
    }



}
