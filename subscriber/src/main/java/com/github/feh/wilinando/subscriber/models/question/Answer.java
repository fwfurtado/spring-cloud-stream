package com.github.feh.wilinando.subscriber.models.question;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Answer {
    private UUID id;
    private UUID questionId;
    private ZonedDateTime date;
    private String comment;
    private Category category;

    public Answer(UUID questionId, String comment, Category category) {
        this.id = UUID.randomUUID();
        this.questionId = questionId;
        this.date = ZonedDateTime.now();
        this.comment = comment;
        this.category = category;
    }

    public UUID getId() {
        return id;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

    public Category getCategory() {
        return category;
    }
}
