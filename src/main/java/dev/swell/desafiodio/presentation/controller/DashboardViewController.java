package dev.swell.desafiodio.presentation.controller;

import dev.swell.desafiodio.architecture.ViewManager;
import dev.swell.desafiodio.architecture.factory.FakerTaskFactory;
import dev.swell.desafiodio.domain.Task;
import dev.swell.desafiodio.domain.TaskState;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardViewController {


    public static final String ViewName = "ui/view/dashboard-view.fxml";


    @FXML
    private TableColumn<Task, LocalDateTime> tbColumnEndDate;

    @FXML
    private TableColumn<Task, Number> tbColumnId;

    @FXML
    private TableColumn<Task, String> tbColumnNote;

    @FXML
    private TableColumn<Task, LocalDateTime> tbColumnStartDate;

    @FXML
    private TableColumn<Task, TaskState> tbColumnState;

    @FXML
    private TableColumn<Task, String> tbColumnTitle;

    @FXML
    private TableView<Task> tbView;

    private String formatDate(LocalDateTime date) {
        return date != null ? date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) : "";
    }

    private void prepareTable() {
        tbColumnId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        tbColumnNote.setCellValueFactory(cellData -> cellData.getValue().noteProperty());
        tbColumnTitle.setCellValueFactory(cellData -> cellData.getValue().titleProperty());


        tbColumnStartDate.setCellValueFactory(cellData -> cellData.getValue().startDateProperty());
        tbColumnState.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
        tbColumnEndDate.setCellValueFactory(cellData -> cellData.getValue().finishDateProperty());

        tbColumnStartDate.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(formatDate(item));
                }

            }
        });

        tbColumnEndDate.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(formatDate(item));
                }

            }
        });

        // Enum para string legível
        tbColumnState.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(TaskState item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getTitle());
                }
            }
        });

        tbView.setRowFactory(tv -> new TableRow<Task>() {
            @Override
            protected void updateItem(Task item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty || isSelected()) {
                    setStyle("");
                } else {
                    switch (item.getState()) {
                        case NOT_STARTED -> setStyle("--fx-background-color: #817d7d;");
                        case COMPLETED -> setStyle("-fx-background-color: lightgreen;");
                        case PENDING -> setStyle("-fx-background-color: lightyellow;");
                        case STARTED -> setStyle("-fx-background-color: lightblue;");
                        case CANCELED -> setStyle("-fx-background-color: lightcoral;");
                        default -> setStyle("");
                    }
                }
            }
        });

    }

    private ObservableList<Task> getTaskList() {
        return FXCollections.observableArrayList(
                FakerTaskFactory.getInstance().createTaskList(20)
        );
    }

    @FXML
    void newTaskButtonAction(ActionEvent event) {
        ViewManager.setView(AddTaskViewController.ViewName);
    }

    @FXML
    private void initialize() {
        prepareTable();
        var list = getTaskList();
        tbView.setItems(list);
    }


}
