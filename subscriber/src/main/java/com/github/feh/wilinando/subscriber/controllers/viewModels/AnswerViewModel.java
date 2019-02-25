package com.github.feh.wilinando.subscriber.controllers.viewModels;

import com.github.feh.wilinando.subscriber.models.AnswerCommand;
import com.github.feh.wilinando.subscriber.models.question.Category;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class AnswerViewModel {
    @NotBlank
    private String comment;

    @NonNull
    private Category category;

    private AnswerViewModel() {}

    public AnswerViewModel(String comment, Category category) {
        this.comment = comment;
        this.category = category;
    }

    public String getComment() {
        return comment;
    }

    public Category getCategory() {
        return category;
    }

    public AnswerCommand toCommand(UUID questionId) {
        return AnswerCommand.from(questionId, comment, category);
    }
}
