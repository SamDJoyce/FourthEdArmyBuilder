package gui.controllers;

import java.util.Map;
import java.util.function.Consumer;

import builder.ArmyBuilder;
import gui.FormatText;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import units.UnitRole;
import units.instances.ModelInstance;
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
    
    private Consumer<UnitInstance> unitSelectionListener;
    
    private FormatText change = new FormatText();

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

    public void setUnitSelectionListener(
            Consumer<UnitInstance> listener) {

        this.unitSelectionListener = listener;
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
    
    private void populateModels(
            UnitInstance unit,
            VBox modelPanel) {

        modelPanel.getChildren().clear();

        for (ModelInstance model : unit.getModels()) {
        	addModelButton(model, modelPanel);
        }
    }

    private void addUnit(
            UnitInstance unit,
            VBox panel) {
        Button unitButton = new Button(
        		change.toTitleCase(unit.getName())
        );

        unitButton.setMaxWidth(Double.MAX_VALUE);

        VBox modelPanel = new VBox(3);
        modelPanel.setVisible(false);
        modelPanel.setManaged(false);

        populateModels(unit, modelPanel);

        unitButton.setOnAction(event -> {
            boolean expanded = modelPanel.isVisible();

            modelPanel.setVisible(!expanded);
            modelPanel.setManaged(!expanded);
            System.out.println(String.format(
            		"Selected instance: %s (id: %s)", 
            		change.toTitleCase(unit.getName()),
            		unit.getId())
            );
            
            VBox unitContainer = new VBox(3);

            unitContainer.getChildren().addAll(
                    unitButton,
                    modelPanel
            );
            
            panel.getChildren().add(unitContainer);
            
            if (unitSelectionListener != null) {
                unitSelectionListener.accept(unit);
            }
        });

        panel.getChildren().add(unitButton);
    }
    
    private void addModelButton(
            ModelInstance model,
            VBox panel) {

        Button button = new Button(
                change.toTitleCase(model.getName()) 
        );

        button.setMaxWidth(Double.MAX_VALUE);
        button.getStyleClass().add("model-button");

        button.setOnAction(event -> {
            System.out.println(String.format(
            		"Selected model: %s (id: %s)",
            		change.toTitleCase(model.getName()),
            		model.getId()
            ));
        });

        panel.getChildren().add(button);
    }

    private void updatePoints() {
        pointsLabel.setText(String.format(
        		"%s/%s", 
        		armyBuilder.getCurrentPoints(),
        		armyBuilder.getPointsLimit())
        );
    }

}