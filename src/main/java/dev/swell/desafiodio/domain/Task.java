package dev.swell.desafiodio.domain;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;

public class Task {

    private final SimpleLongProperty id = new SimpleLongProperty();
    private final SimpleStringProperty title = new SimpleStringProperty();
    private final SimpleStringProperty description = new SimpleStringProperty();
    private final SimpleObjectProperty<TaskState> state = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<LocalDateTime> startDate = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<LocalDateTime> finishDate = new SimpleObjectProperty<>();
    private final SimpleStringProperty note = new SimpleStringProperty();

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public TaskState getState() {
        return state.get();
    }

    public SimpleObjectProperty<TaskState> stateProperty() {
        return state;
    }

    public LocalDateTime getStartDate() {
        return startDate.get();
    }

    public SimpleObjectProperty<LocalDateTime> startDateProperty() {
        return startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate.get();
    }

    public SimpleObjectProperty<LocalDateTime> finishDateProperty() {
        return finishDate;
    }

    public String getNote() {
        return note.get();
    }

    public SimpleStringProperty noteProperty() {
        return note;
    }


}
