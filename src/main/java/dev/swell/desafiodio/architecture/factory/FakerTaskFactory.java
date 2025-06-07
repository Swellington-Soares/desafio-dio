package dev.swell.desafiodio.architecture.factory;

import dev.swell.desafiodio.architecture.mapper.TaskMapper;
import dev.swell.desafiodio.domain.Task;
import dev.swell.desafiodio.domain.TaskState;
import dev.swell.desafiodio.entity.TaskEntity;
import net.datafaker.Faker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FakerTaskFactory {

    private Long sequence = 1L;

    private final Faker faker = new Faker();

    private static final FakerTaskFactory INSTANCE = new FakerTaskFactory();

    private FakerTaskFactory() {}

    public static FakerTaskFactory getInstance() {
        return INSTANCE;
    }

    public Task createTask() {
        return TaskMapper.toTask(createTaskEntity());
    }

    public List<Task> createTaskList(int amount) {
        if  (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        return IntStream.range(1, amount).mapToObj(i -> createTask()).toList();
    }

    public TaskEntity createTaskEntity() {
        var taskEntity = new TaskEntity();
        taskEntity.setId(sequence++);
        taskEntity.setNote(faker.lorem().paragraph(1));
        taskEntity.setTitle(faker.book().title());
        taskEntity.setDescription(faker.lorem().paragraph(2));
        taskEntity.setState(  TaskState.values()[new Random().nextInt(TaskState.values().length)] );
        taskEntity.setStartDate(LocalDateTime.now());
        taskEntity.setEndDate(LocalDateTime.now().plusDays(4));
        return taskEntity;
    }

    public List<TaskEntity> createTaskEntityList(int amount) {
        if  (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        return IntStream.range(1, amount).mapToObj(i -> createTaskEntity()).toList();
    }

}
