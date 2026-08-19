package gui;

import java.io.IOException;

import builder.ArmyBuilder;
import gui.controllers.UnitSelectionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import loaders.CodexLoader;
import roster.Codex;

public class ABMain extends Application {
    @Override
    public void start(Stage stage) {

    	CodexLoader loader = new CodexLoader("src/main/resources/json/codex space marines");
    	Codex codex  = loader.loadCodex();
    	ArmyBuilder armyBuilder = new ArmyBuilder(codex);
    	
        try {
            FXMLLoader xmlLoader = new FXMLLoader(
                    getClass().getResource("/gui/MainView.fxml")
            );

            BorderPane root = xmlLoader.load();

            UnitSelectionController controller =
            		xmlLoader.getController();

            controller.setArmyBuilder(armyBuilder);

            Scene scene = new Scene(root, 1000, 700);

            stage.setTitle("Fourth Ed Army Builder");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
