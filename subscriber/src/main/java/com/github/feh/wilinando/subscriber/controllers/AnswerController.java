package com.github.feh.wilinando.subscriber.controllers;

import com.github.feh.wilinando.subscriber.controllers.viewModels.AnswerViewModel;
import com.github.feh.wilinando.subscriber.events.CommandEvent;
import com.github.feh.wilinando.subscriber.infra.Logger;
import com.github.feh.wilinando.subscriber.models.AnswerCommand;
import com.github.feh.wilinando.subscriber.models.question.Category;
import com.github.feh.wilinando.subscriber.repositories.AnswerCommandRepository;
import com.github.feh.wilinando.subscriber.repositories.AnswerQueryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("question")
public class AnswerController {
    private final MessageChannel event;
    private final AnswerCommandRepository commandRepository;
    private final AnswerQueryRepository queryRepository;
    private final Logger<AnswerController> logger;

    public AnswerController(CommandEvent commandEvent, AnswerCommandRepository commandRepository, AnswerQueryRepository queryRepository, Logger<AnswerController> logger) {
        this.event = commandEvent.input();
        this.commandRepository = commandRepository;
        this.queryRepository = queryRepository;
        this.logger = logger;
    }



    @PostMapping("{questionId}")
    public ResponseEntity<?> save(@PathVariable UUID questionId, @RequestBody @Valid AnswerViewModel viewModel){
        AnswerCommand command  = viewModel.toCommand(questionId);

        event
            .send(MessageBuilder.withPayload(command)
                .build());

        logger.info("Saved a new answer %s", command);

        return ResponseEntity.ok(command);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnswerCommand> getBy(@PathVariable UUID id){
        return commandRepository
                .findById(id)
                    .map(ResponseEntity::ok)
                        .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("admin/metrics")
    public Map<Category, Long> getMetrics(){
        return queryRepository.findAll();
    }
}
