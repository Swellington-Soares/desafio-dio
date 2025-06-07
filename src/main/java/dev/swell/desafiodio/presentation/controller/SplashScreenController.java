package dev.swell.desafiodio.presentation.controller;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;

@SuppressWarnings("BusyWait")
public class SplashScreenController {

    private final int WAIT_TIME = 250;

    @FXML
    private AnchorPane root;

    @FXML
    private ProgressBar pbProgress;

    @FXML
    private Text progressText;

    private final List<String> progressMessages = Arrays.asList(
            "Loading resources...",
            "Loading user managers...",
            "Loading interfaces...",
            "Preparing environment...",
            "Finishing..."
    );

    @Setter
    private Runnable onFinish;

    public void startLoading() {
        new Thread(() -> {
            try {
                for (int i = 0; i < progressMessages.size(); i++) {
                    int index = i;
                    Platform.runLater(() -> {
                        progressText.setText(progressMessages.get(index));
                        pbProgress.setProgress((index + 1) / (double) progressMessages.size());
                    });
                    sleep(WAIT_TIME);
                }
                Platform.runLater(this::fadeOutAndContinue);
            } catch (Exception ignored) {}
        }).start();
    }

    private void fadeOutAndContinue() {
        FadeTransition fade = new FadeTransition(Duration.seconds(1), root);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setOnFinished(e -> {
            if (onFinish != null) onFinish.run();
        });
        fade.play();
    }


}
