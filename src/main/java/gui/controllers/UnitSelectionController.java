package gui.controllers;

import java.util.Map;

import builder.ArmyBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import roster.Codex;
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
                UnitRole.HQ, hqPanel,
                UnitRole.ELITES, elitesPanel,
                UnitRole.TROOPS, troopsPanel,
                UnitRole.FAST_ATTACK, fastAttackPanel,
                UnitRole.HEAVY_SUPPORT, heavySupportPanel,
                UnitRole.NONE, nonePanel
            );
    }
    
    public void setArmyBuilder(ArmyBuilder armyBuilder) {
        this.armyBuilder = armyBuilder;
        populateUnits();
    }

    private void populateUnits() {

        Codex codex = armyBuilder.getCodex();
        for (UnitRole role : UnitRole.values()) {
        	VBox panel = panels.get(role);
        	if (panel != null) {
            	populateRole(
            			codex, 
            			role, 
            			panel);
        	}

        }
    }

    private void populateRole(
            Codex codex,
            UnitRole role,
            VBox panel) {

        panel.getChildren().clear();
        for (UnitDescription unit : codex.getUnitsByRole(role)) {
                addUnitButton(unit, panel);
        }
    }

    private void addUnitButton(
            UnitDescription unit,
            VBox panel) {

        Button button = new Button(unit.getName());

        button.setMaxWidth(Double.MAX_VALUE);

        button.setOnAction(event -> {
            System.out.println(
                "Selected unit: " + unit.getName()
            );
        });

        panel.getChildren().add(button);
    }
}