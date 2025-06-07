package dev.swell.desafiodio;

import dev.swell.desafiodio.presentation.controller.SplashScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class TaskApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(TaskApplication.class.getResource("ui/view/splash-screen.fxml"));
        Parent root = fxmlLoader.load();
        SplashScreenController controller = fxmlLoader.getController();
        Scene scene = new Scene(root);
        Stage splashStage = new Stage(StageStyle.TRANSPARENT);
        splashStage.setScene(scene);
        splashStage.setResizable(false);
        splashStage.setAlwaysOnTop(true);
        splashStage.show();

        controller.setOnFinish(() -> {
            try {
                splashStage.close();
                showMainStage(stage);
            } catch (IOException ignored) {}
        });

        controller.startLoading();
    }

    private void showMainStage(Stage root) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TaskApplication.class.getResource("ui/view/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        root.setScene(scene);
        root.show();
    }

    public static void main(String[] args) {
        launch();
    }
}