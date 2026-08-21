package gui.controllers;

import java.util.Map;

import builder.ArmyBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import units.UnitRole;
import units.instances.UnitInstance;

public class RosterController {

    @FXML
    private VBox hqPanel;

    @FXML
    private VBox elitesPanel;

    @FXML
    private VBox troopsPanel;

    @FXML
    private VBox fastAttackPanel;

    @FXML
    private VBox heavySupportPanel;

    @FXML
    private VBox nonePanel;

    @FXML
    private Label pointsLabel;

    private ArmyBuilder armyBuilder;
    
    private Map<UnitRole, VBox> panels;

    @FXML
    private void initialize() {
        panels = Map.of(
                UnitRole.HQ, 	 		hqPanel,
                UnitRole.ELITES,		elitesPanel,
                UnitRole.TROOPS, 	  	troopsPanel,
                UnitRole.FAST_ATTACK, 	fastAttackPanel,
                UnitRole.HEAVY_SUPPORT, heavySupportPanel,
                UnitRole.NONE, 			nonePanel
            );
    }

    public void setArmyBuilder(ArmyBuilder armyBuilder) {
        this.armyBuilder = armyBuilder;
        refresh();
    }

    public void refresh() {
    	
    	
        for (UnitRole role : UnitRole.values()) {
        	VBox panel = panels.get(role);
        	if (panel != null) {
            	populateRole( 
            			role, 
            			panel);
        	}
        }
        updatePoints();
    }

    private void populateRole(
            UnitRole role,
            VBox panel) {

        panel.getChildren().clear();

        for (UnitInstance unit :
                armyBuilder.getUnitInstancesByRole(role)) {
            addUnit(unit, panel);
        }
    }

    private void addUnit(
            UnitInstance unit,
            VBox panel) {
    	// In future this should be a unit view which 
    	// displays all the unit's options and info
        Button button = new Button(
                unit.getDescription().getName()
        );

        button.setMaxWidth(Double.MAX_VALUE);

        button.setOnAction(event -> {
            System.out.println(
                    "Selected instance: "
                    + unit.getName()
            );
        });

        panel.getChildren().add(button);
    }

    private void updatePoints() {
        pointsLabel.setText(
                armyBuilder.getCurrentPoints()
                + " / "
                + armyBuilder.getPointsLimit()
        );
    }
}