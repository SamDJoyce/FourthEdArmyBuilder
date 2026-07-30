package gui;

import gui.views.ABMainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ABMain extends Application {
    @Override
    public void start(Stage stage) {

        ABMainView root = new ABMainView();

        Scene scene = new Scene(root, 1000, 700);

        stage.setTitle("Fourth Ed Army Builder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
