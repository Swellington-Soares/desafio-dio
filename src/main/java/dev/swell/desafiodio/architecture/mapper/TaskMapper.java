package dev.swell.desafiodio.architecture.mapper;

import dev.swell.desafiodio.domain.Task;
import dev.swell.desafiodio.entity.TaskEntity;

public class TaskMapper {

    public static Task toTask(TaskEntity taskEntity) {
        var task = new Task();
        task.idProperty().set( taskEntity.getId() );
        task.titleProperty().set( taskEntity.getTitle() );
        task.descriptionProperty().set( taskEntity.getDescription() );
        task.startDateProperty().set( taskEntity.getStartDate() );
        task.finishDateProperty().set( taskEntity.getEndDate() );
        task.stateProperty().set( taskEntity.getState() );
        task.noteProperty().set( taskEntity.getNote() );
        return task;
    }

    public static TaskEntity toTaskEntity(Task task) {
        var taskEntity = new TaskEntity();
        taskEntity.setId( task.getId() );
        taskEntity.setTitle( task.getTitle() );
        taskEntity.setDescription( task.getDescription() );
        taskEntity.setStartDate( task.getStartDate() );
        taskEntity.setEndDate( task.getFinishDate() );
        taskEntity.setState( task.getState() );
        taskEntity.setNote( task.getNote() );
        return taskEntity;
    }
}
