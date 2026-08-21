package gui.controllers;

import java.util.Map;

import builder.ArmyBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import roster.ValidationResult;
import units.UnitRole;
import units.descriptions.UnitDescription;

public class UnitSelectionController {

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
        System.out.println("UnitSelectionController: setArmyBuilder called");

        this.armyBuilder = armyBuilder;

        System.out.println(
            "Codex: " + armyBuilder.getCodex()
        );
        populateUnits();
    }

    private void populateUnits() {
    	System.out.println("populateUnits() called");
        for (UnitRole role : UnitRole.values()) {
        	VBox panel = panels.get(role);
        	
            System.out.println(
                    "Role: " + role +
                    ", panel: " + panel
                );
        	
        	if (panel != null) {
            	populateRole( 
            			role, 
            			panel);
        	}
        }
    }

    private void populateRole(
            UnitRole role,
            VBox panel) {
    	
        var units = armyBuilder.getUnitDescriptionsByRole(role);
        System.out.println(
            role + " units: " + units.size()
        );
        
        panel.getChildren().clear();
        for (UnitDescription unit : armyBuilder.getUnitDescriptionsByRole(role)) {
        	System.out.println(
                    "Adding button: " + unit.getName()
                );
        	addUnitButton(unit, panel);
        }
    }

    private void addUnitButton(
            UnitDescription unit,
            VBox panel) {

        Button button = new Button(unit.getName());

        button.setMaxWidth(Double.MAX_VALUE);

        button.setOnAction(event -> {
        	ValidationResult result = armyBuilder.addUnit(unit);
            System.out.println(
                "Selected unit:" + unit.getName()
            );
            System.out.println(
            	"Validation Result:\n" + result.getMessage());
        });

        panel.getChildren().add(button);
    }
}