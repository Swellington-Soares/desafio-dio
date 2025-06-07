package dev.swell.desafiodio.presentation.controller;

import dev.swell.desafiodio.TaskApplication;
import dev.swell.desafiodio.architecture.ViewManager;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

public class MainController {

    @FXML
    public StackPane root;


    @FXML
    private void initialize() throws IOException {

        ViewManager.getInstance().currentViewProperty().addListener((observable, oldValue, newValue) -> {
            try {
                switchView(newValue);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        ViewManager.setView(DashboardViewController.ViewName);
    }

    public void switchView(@NotNull String fxmlFile) throws IOException {
        Parent newView = FXMLLoader.load(Objects.requireNonNull( TaskApplication.getResource(fxmlFile)));

        if (!root.getChildren().isEmpty()) {
            Node oldView = root.getChildren().get(0);

            FadeTransition fadeOut = new FadeTransition(Duration.millis(300), oldView);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);

            fadeOut.setOnFinished(e -> {
                root.getChildren().clear();
                root.getChildren().add(newView);

                FadeTransition fadeIn = new FadeTransition(Duration.millis(300), newView);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });

            fadeOut.play();
        } else {
            root.getChildren().add(newView);

            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), newView);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        }
    }


}