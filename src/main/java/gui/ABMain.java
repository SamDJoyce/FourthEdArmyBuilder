package gui;

import java.io.IOException;

import builder.ArmyBuilder;
import gui.controllers.RosterController;
import gui.controllers.UnitConfigurationController;
import gui.controllers.UnitSelectionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import loaders.CodexLoader;

public class ABMain extends Application {
    @Override
    public void start(Stage stage) {
    	String selectedCodex = "/json/codex space marines";
    	CodexLoader loader = new CodexLoader(selectedCodex);
    	ArmyBuilder armyBuilder = new ArmyBuilder(loader.loadCodex());
    	
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
            
            // *** Load the Unit Configuration View ***
            FXMLLoader configurationLoader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/gui/UnitConfigurationView.fxml"
                            )
                    );

            Node unitConfigurationView =
                    configurationLoader.load();

            UnitConfigurationController
                    unitConfigurationController =
                        configurationLoader.getController();
            

            // *** Give all controllers the same Army Builder ***
            unitSelectionController.setArmyBuilder(
                    armyBuilder);
            rosterController.setArmyBuilder(
                    armyBuilder);
            unitConfigurationController.setArmyBuilder(
                    armyBuilder);
            
            // *** Connect listeners ***
            rosterController.setUnitSelectionListener(
            		unitConfigurationController::setOwner);
            unitConfigurationController.setRosterRefresh(
            		rosterController::refresh);
            unitSelectionController.setRosterRefresh(
            	    rosterController::refresh);
            
            // *** Put the views into the main layout ***
            root.setLeft(unitSelectionView);
            root.setCenter(rosterView);
            root.setRight(unitConfigurationView);
            
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
