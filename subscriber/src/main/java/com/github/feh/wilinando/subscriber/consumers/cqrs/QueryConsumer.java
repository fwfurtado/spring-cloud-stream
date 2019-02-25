package com.github.feh.wilinando.subscriber.consumers.cqrs;

import com.github.feh.wilinando.subscriber.events.QueryEvent;
import com.github.feh.wilinando.subscriber.models.AnswerCommand;
import com.github.feh.wilinando.subscriber.repositories.AnswerQueryRepository;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(QueryEvent.class)
public class QueryConsumer {

    private final AnswerQueryRepository repository;

    public QueryConsumer(AnswerQueryRepository repository) {
        this.repository = repository;
    }

    @StreamListener(QueryEvent.CHANNEL)
    public void answerConsumer(AnswerCommand command){
        repository.saveBy(command);
    }

}
