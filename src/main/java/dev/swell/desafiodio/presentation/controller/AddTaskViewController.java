package dev.swell.desafiodio.presentation.controller;

import java.net.URL;
import java.util.ResourceBundle;

import dev.swell.desafiodio.domain.TaskState;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AddTaskViewController {

    public static final String ViewName = "ui/view/add-task-view.fxml";

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<TaskState> comboBoxTaskState;

    @FXML
    private DatePicker datePickerFinishTask;

    @FXML
    private DatePicker datePickerStartTask;

    @FXML
    private Text datePickerTaskStartError;

    @FXML
    private Text datePickerTextFinishError;

    @FXML
    private VBox root;

    @FXML
    private TextArea textAreaDescription;

    @FXML
    private Text textDescriptionCount;

    @FXML
    private Text textDescriptionError;

    @FXML
    private TextField textFieldTitle;

    @FXML
    private Text textTitleError;

    @FXML
    void cadastrarButtonAction(ActionEvent event) {

    }

    @FXML
    void cancelButtonAction(ActionEvent event) {

    }

    void initializeComboboxTaskState() {

        comboBoxTaskState.setItems(FXCollections.observableArrayList(TaskState.values()));

        comboBoxTaskState.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(TaskState item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getTitle());
                }
            }
        });

        comboBoxTaskState.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(TaskState item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getTitle());
                    setStyle("");
                }
            }
        });

        comboBoxTaskState.getSelectionModel().clearSelection();
    }

    private void setErrorViewInvisible() {
        datePickerTaskStartError.setVisible(false);
        datePickerTextFinishError.setManaged(false);
        textDescriptionError.setVisible(false);
        textTitleError.setVisible(false);
    }

    @FXML
    void initialize() {
        initializeComboboxTaskState();
        initializeTextDescription();
        setErrorViewInvisible();
    }

    private void initializeTextDescription() {
        textAreaDescription.textProperty().addListener((observable, oldValue, newValue) -> {
            textDescriptionCount.setText("");
        });
    }


}
