package com.github.feh.wilinando.subscriber.repositories;

import com.github.feh.wilinando.subscriber.models.AnswerCommand;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AnswerCommandRepository {

    private static Map<UUID, AnswerCommand> database = new HashMap<>();

    public void save(AnswerCommand command){
        database.put(command.getId(), command);

        System.out.println("Saving --> " + command);
    }

    public Optional<AnswerCommand> findById(UUID id) {
        return Optional.ofNullable(database.get(id));
    }
}
