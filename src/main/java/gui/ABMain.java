package gui;

import builder.ArmyBuilder;
import gui.views.ABMainView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import loaders.CodexLoader;
import roster.Codex;

public class ABMain extends Application {
    @Override
    public void start(Stage stage) {

    	CodexLoader loader = new CodexLoader("src/main/resources/json/codex space marines");
    	Codex codex  = loader.loadCodex();
    	ArmyBuilder armyBuilder = new ArmyBuilder(codex);
    	FXMLLoader loaderFXML = new FXMLLoader(
    				getClass().getResource("/gui/MainView.fxml")
    			);
    	
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
