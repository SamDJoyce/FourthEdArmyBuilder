package gui;

import java.io.IOException;
import java.util.List;

import builder.ArmyBuilder;
import forceOrg.ForceOrgChart;
import gui.controllers.RosterController;
import gui.controllers.RosterSetup;
import gui.controllers.RosterSetupController;
import gui.controllers.UnitConfigurationController;
import gui.controllers.UnitSelectionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import loaders.CodexLoader;
import loaders.ForceOrgChartLoader;
import loaders.LoaderFactory;

public class ABMain extends Application {
	

	
	@Override
	public void start(Stage stage) {

	    ForceOrgChartLoader chartLoader =
	            LoaderFactory.forOrgChart();

	    CodexLoader loader =
	            new CodexLoader("/json/codex space marines");

	    try {
	        List<ForceOrgChart> charts =
	                chartLoader.load("/json/forceOrgCharts.json");

	        FXMLLoader setupLoader =
	                new FXMLLoader(
	                    getClass().getResource(
	                        "/gui/RosterSetupView.fxml"
	                    )
	                );

	        VBox setupRoot = setupLoader.load();

	        RosterSetupController setupController =
	                setupLoader.getController();

	        setupController.setForceOrgCharts(charts);

	        setupController.setOnCreate(
	                setup -> createRoster(
	                    stage,
	                    setup,
	                    loader
	                )
	        );

	        Scene scene = new Scene(
	                setupRoot,
	                500,
	                400
	        );

	        stage.setTitle("Fourth Ed Army Builder");
	        stage.setScene(scene);
	        stage.show();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

    private void createRoster(
            Stage stage,
            RosterSetup setup,
            CodexLoader loader) {

        try {

            ArmyBuilder armyBuilder =
                    new ArmyBuilder(
                        setup.name(),
                        loader.loadCodex(),
                        setup.forceOrgChart(),
                        setup.pointsLimit()
                    );

            // Load Main View
            FXMLLoader mainLoader =
                    new FXMLLoader(
                        getClass().getResource(
                            "/gui/MainView.fxml"
                        )
                    );

            BorderPane root = mainLoader.load();

            // Load Unit Selection View
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

            // Load Roster View
            FXMLLoader rosterLoader =
                    new FXMLLoader(
                        getClass().getResource(
                            "/gui/RosterView.fxml"
                        )
                    );

            Node rosterView =
                    rosterLoader.load();

            RosterController rosterController =
                    rosterLoader.getController();

            // Load Unit Configuration View
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

            // Give controllers the ArmyBuilder
            unitSelectionController.setArmyBuilder(
                    armyBuilder
            );

            rosterController.setArmyBuilder(
                    armyBuilder
            );

            unitConfigurationController.setArmyBuilder(
                    armyBuilder
            );

            // Connect listeners
            rosterController.setUnitSelectionListener(
                    unitConfigurationController::setOwner
            );

            unitConfigurationController.setRosterRefresh(
                    rosterController::refresh
            );

            unitSelectionController.setRosterRefresh(
                    rosterController::refresh
            );

            // Put views into main layout
            root.setLeft(unitSelectionView);
            root.setCenter(rosterView);
            root.setRight(unitConfigurationView);

            Scene scene =
                    new Scene(root, 1000, 700);

            stage.setTitle(
                    setup.name() + " - Fourth Ed Army Builder"
            );

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
