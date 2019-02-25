package com.github.feh.wilinando.subscriber.models;

import com.github.feh.wilinando.subscriber.models.question.Answer;
import com.github.feh.wilinando.subscriber.models.question.Category;

import java.time.ZonedDateTime;
import java.util.UUID;

public class AnswerCommand implements Command<AnswerCommand> {

    private Answer answer;

    private AnswerCommand() {}

    public AnswerCommand(Answer answer) {
        this.answer = answer;
    }

    public static AnswerCommand from(UUID questionId, String comment, Category category) {
        Answer answer = new Answer(questionId, comment, category);
        return new AnswerCommand(answer);
    }

    public UUID getId() {
        return answer.getId();
    }

    public UUID getQuestionId() {
        return answer.getQuestionId();
    }

    public ZonedDateTime getDate() {
        return answer.getDate();
    }

    public String getComment() {
        return answer.getComment();
    }

    public Category getCategory() {
        return answer.getCategory();
    }

    @Override
    public UUID getCorrelationId() {
        return answer.getId();
    }
}
