package gui;

import java.io.IOException;

import builder.ArmyBuilder;
import gui.controllers.RosterController;
import gui.controllers.UnitSelectionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import loaders.CodexLoader;
import roster.Codex;

public class ABMain extends Application {
    @Override
    public void start(Stage stage) {

    	CodexLoader loader = new CodexLoader("/json/codex space marines");
    	Codex codex  = loader.loadCodex();
    	ArmyBuilder armyBuilder = new ArmyBuilder(codex);
    	
        try {
        	// *** Load Main View ***
            FXMLLoader mainLoader = new FXMLLoader(
                    getClass().getResource("/gui/MainView.fxml")
            );

            BorderPane root = mainLoader.load();

            // *** Load UnitSelectionView ***
            FXMLLoader unitSelectionLoader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/gui/UnitSelectionView.fxml"
                            )
                    );
            Node unitSelectionView =
                    unitSelectionLoader.load();
            UnitSelectionController unitSelectionController =
            		unitSelectionLoader.getController();
            
            // *** Load the Roster View ***
            FXMLLoader rosterLoader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/gui/RosterView.fxml"));

            Node rosterView =
                    rosterLoader.load();

            RosterController rosterController =
                    rosterLoader.getController();

            // Give both controllers the same Army Builder
            unitSelectionController.setArmyBuilder(
                    armyBuilder);
            
            unitSelectionController.setRosterRefresh(
            	    rosterController::refresh);

            rosterController.setArmyBuilder(
                    armyBuilder);
            
            
            // *** Put the views into the main layout ***
            root.setLeft(unitSelectionView);
            root.setCenter(rosterView);
            
            // *** Create and display the window ***
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
