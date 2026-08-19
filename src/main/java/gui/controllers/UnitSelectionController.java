package gui.controllers;

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

    public void setArmyBuilder(ArmyBuilder armyBuilder) {
        this.armyBuilder = armyBuilder;
        populateUnits();
    }

    private void populateUnits() {

        Codex codex = armyBuilder.getCodex();
        
        populateRole(codex, UnitRole.HQ, hqPanel);
        populateRole(codex, UnitRole.ELITES, elitesPanel);
        populateRole(codex, UnitRole.TROOPS, troopsPanel);
        populateRole(codex, UnitRole.FAST_ATTACK, fastAttackPanel);
        populateRole(codex, UnitRole.HEAVY_SUPPORT, heavySupportPanel);
        populateRole(codex, UnitRole.NONE, nonePanel);
        
        
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