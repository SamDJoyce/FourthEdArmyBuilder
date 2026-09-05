package gui.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import builder.ArmyBuilder;
import gui.FormatText;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import roster.ValidationResult;
import units.UnitRole;
import units.instances.ModelInstance;
import units.instances.UnitInstance;
import units.instances.WargearInstance;
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
    
    private Set<String> expandedUnits = new HashSet<>();

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
        }
    }

    private void addUnit(
            UnitInstance unit,
            VBox panel) {
    	VBox unitContainer = new VBox(3);

        HBox unitHeader = new HBox(5);

        Button unitButton = new Button(
                change.toTitleCase(unit.getName())
        );

        unitButton.setMaxWidth(Double.MAX_VALUE);
        unitButton.getStyleClass().add("unit-button");

        Button removeButton = new Button("X");
        removeButton.setMaxWidth(30);

        VBox modelPanel = new VBox(3);

        boolean expanded = expandedUnits.contains(unit.getId());

        modelPanel.setVisible(expanded);
        modelPanel.setManaged(expanded);

        populateModels(unit, modelPanel);

        unitButton.setOnAction(event -> {

            boolean currentlyExpanded =
                    expandedUnits.contains(unit.getId());

            if (currentlyExpanded) {
                expandedUnits.remove(unit.getId());
            } else {
                expandedUnits.add(unit.getId());
            }

            boolean newExpandedState = !currentlyExpanded;

            modelPanel.setVisible(newExpandedState);
            modelPanel.setManaged(newExpandedState);

            selectForConfig(unit);
        });

        removeButton.setOnAction(event -> {
            expandedUnits.remove(unit.getId());
            armyBuilder.removeUnit(unit);
            refresh();
        });

        unitHeader.getChildren().addAll(
                unitButton,
                removeButton
        );

        unitContainer.getChildren().addAll(
                unitHeader,
                modelPanel
        );

        panel.getChildren().add(unitContainer);
    }
    
    private String constructGearList(ModelInstance model) {
    	List<WargearInstance> gearList = new ArrayList<>(
    									 	model.getGear());
    	gearList.sort(Comparator.comparing(
    				WargearInstance::getName,
    				String.CASE_INSENSITIVE_ORDER
    			));
    	
    	String gearText = new String();
    	
    	for (WargearInstance i : gearList) {

    		if (i.equals(gearList.getLast())) {
        		gearText += String.format(
        				"%s", 
        				i.getName());
    		} else {
        		gearText += String.format(
        				"%s, ", 
        				i.getName());
    		}
    	}
    	
    	return gearText;
    }
    
    private void addModel(
            ModelInstance model,
            VBox panel) {

        Button modelButton = new Button(
                change.toTitleCase(model.getName()).trim()
        );
        
        Label modelLabel = new Label(
        						constructGearList(model));

        modelButton.setMaxWidth(Double.MAX_VALUE);
        modelButton.setMaxHeight(Double.MAX_VALUE);
        modelButton.getStyleClass().add("model-button");

        Button removeButton = new Button("X");
        removeButton.setMaxWidth(30);

        modelButton.setOnAction(event -> {
//            System.out.println(String.format(
//                    "Selected model: %s\n  -id: %s\n  -types:%s",
//                    change.toTitleCase(model.getName()),
//                    model.getId(),
//                    model.getTypes()
//            ));

            selectForConfig(model);
        });

        removeButton.setOnAction(event -> {
            ValidationResult result =
                    armyBuilder.removeModel(model);

            if (result.isValid()) {
                System.out.println(
                        model.getName() + " removed"
                );
                refresh();
            } else {
                System.out.println(
                        "Cannot remove model " + model.getName()
                );
                System.out.println(
                        "\n" + result.getMessage()
                );
            }
        });

        HBox modelRow = new HBox(5);

        modelRow.getChildren().addAll(
                modelButton,
                removeButton
        );

        panel.getChildren().addAll(modelRow, modelLabel);
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