package gui.controllers;

import java.util.Map;
import java.util.function.Consumer;

import builder.ArmyBuilder;
import gui.FormatText;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import roster.ValidationResult;
import units.UnitRole;
import units.instances.ModelInstance;
import units.instances.UnitInstance;
import units.options.OptionOwner;

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
    
    @FXML
    private Label validationLabel;

    private ArmyBuilder armyBuilder;
    
    private Map<UnitRole, VBox> panels;
    
    private Consumer<OptionOwner> selectionListener;
    
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
            Consumer<OptionOwner> listener) {

        this.selectionListener = listener;
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
        updateValidation();
        
    }
    
    private void updateValidation() {

        ValidationResult result = armyBuilder.validateRoster();

        validationLabel.setText(result.getMessage());
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
        	addModel(model, modelPanel);
        	removeModelButton(model, modelPanel);
        }
    }

    private void addUnit(
            UnitInstance unit,
            VBox panel) {
        Button unitButton = new Button(
        		change.toTitleCase(unit.getName())
        );

        unitButton.setMaxWidth(Double.MAX_VALUE - 10);

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
            
            selectForConfig(unit);
        });

        panel.getChildren().add(unitButton);
        removeUnitButton(unit, panel);
    }
    
    private void removeUnitButton(
    		UnitInstance unit,
    		VBox panel) {
    	Button removeButton = new Button("X");
    	removeButton.setMaxWidth(10);
    	
    	removeButton.setOnAction(event -> {
    		ValidationResult result = armyBuilder.removeUnit(unit);
    		refresh();
    	});
    	panel.getChildren().add(removeButton);
    }
    
    private void removeModelButton(
    		ModelInstance model,
    		VBox panel) {
    	Button removeButton = new Button("X");
    	removeButton.setMaxWidth(10);
    	
    	removeButton.setOnAction(event -> {
    		ValidationResult result = armyBuilder.removeModel(model);
    		if (result.isValid()) {
    			System.out.println(model.getName() + " removed");
    			//System.out.println("There are now " + model.getParentUnit().getCurrentSize() + " models in this unit");
    			refresh();
    		} else {
    			System.out.println("Cannot remove model " + model.getName());
    			System.out.println("\n" + result.getMessage());
    		}
    	});
    	panel.getChildren().add(removeButton);
    }
    
    private void addModel(
            ModelInstance model,
            VBox panel) {

    	String buttonText = String.format(
    							"%s\n%s", 
    							model.getName(),
    							model.getGear());
    	
        Button button = new Button(
                change.toTitleCase(buttonText) 
        );

        button.setMaxWidth(Double.MAX_VALUE);
        button.setMaxHeight(Double.MAX_VALUE);
        button.getStyleClass().add("model-button");

        button.setOnAction(event -> {
            System.out.println(String.format(
            		"Selected model: %s (id: %s)",
            		change.toTitleCase(model.getName()),
            		model.getId()
            ));
            selectForConfig(model);
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
    
    private void selectForConfig(OptionOwner owner) {
        if (selectionListener != null) {
            selectionListener.accept(owner);
        }
    }

}