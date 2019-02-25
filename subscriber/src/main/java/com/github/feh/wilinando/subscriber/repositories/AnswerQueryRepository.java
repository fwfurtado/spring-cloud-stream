package com.github.feh.wilinando.subscriber.repositories;

import com.github.feh.wilinando.subscriber.models.AnswerCommand;
import com.github.feh.wilinando.subscriber.models.question.Category;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AnswerQueryRepository {
    private static Map<Category, Long> quantityByCategory = new HashMap<>();

    public void saveBy(AnswerCommand command) {
        Category category = command.getCategory();
        quantityByCategory.merge(category, 1L, Long::sum);

        System.out.println(quantityByCategory);
    }

    public Map<Category, Long> findAll() {
        return Collections.unmodifiableMap(quantityByCategory);
    }
}
