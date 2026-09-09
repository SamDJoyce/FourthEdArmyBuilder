package gui.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import builder.ArmyBuilder;
import gui.FormatText;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import roster.ValidationResult;
import units.UnitRole;
import units.descriptions.models.StatLine;
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
    
    private GridPane addStats(UnitInstance unit) {

        GridPane statsGrid = new GridPane();

        statsGrid.setHgap(5);
        statsGrid.setVgap(2);
        statsGrid.setPadding(new Insets(3, 0, 5, 0));

        List<ModelInstance> models = unit.getUniqueModels();

        if (models.isEmpty()) {
            return statsGrid;
        }

        /*
         * Use the statline type of each unique model.
         */
        for (ModelInstance model : models) {

            StatLine stats = model.getStats();

            switch (stats.getType().toLowerCase()) {

                case "infantry":
                    addInfantryStats(statsGrid, model);
                    break;

                case "vehicle":
                    addVehicleStats(statsGrid, model);
                    break;

                case "walker":
                    addWalkerStats(statsGrid, model);
                    break;

                default:
                    System.out.println(
                        "Unknown statline type: " + stats.getType()
                    );
                    break;
            }
        }

        return statsGrid;
    }
    
    private void addInfantryStats(
            GridPane grid,
            ModelInstance model) {

        StatLine stats = model.getStats();

        int row = grid.getRowCount();

        if (row == 0) {
            addHeaders(
                grid,
                "", "WS", "BS", "S", "T",
                "W", "I", "A", "Ld", "Sv"
            );
            row = 1;
        }

        addStatRow(
            grid,
            row,
            change.toTitleCase(model.getName()),
            stats.getWs(),
            stats.getBs(),
            stats.getS(),
            stats.getT(),
            stats.getW(),
            stats.getI(),
            stats.getA(),
            stats.getLd(),
            stats.getSv()
        );
    }
    
    private void addVehicleStats(
            GridPane grid,
            ModelInstance model) {

        StatLine stats = model.getStats();

        int row = grid.getRowCount();

        if (row == 0) {
            addHeaders(
                grid,
                "", "BS", "Front", "Side", "Rear"
            );
            row = 1;
        }

        addStatRow(
            grid,
            row,
            change.toTitleCase(model.getName()) ,
            stats.getBs(),
            stats.getFront(),
            stats.getSide(),
            stats.getRear()
        );
    }
    
    private void addWalkerStats(
            GridPane grid,
            ModelInstance model) {

        StatLine stats = model.getStats();

        int row = grid.getRowCount();

        if (row == 0) {
            addHeaders(
                grid,
                "", "WS", "BS", "S", "I",
                "A", "Front", "Side", "Rear"
            );
            row = 1;
        }

        addStatRow(
            grid,
            row,
            change.toTitleCase(model.getName()),
            stats.getWs(),
            stats.getBs(),
            stats.getS(),
            stats.getI(),
            stats.getA(),
            stats.getFront(),
            stats.getSide(),
            stats.getRear()
        );
    }

    private void addHeaders(
            GridPane grid,
            String... headers) {

        for (int column = 0; column < headers.length; column++) {

            Label label = new Label(headers[column]);

            label.getStyleClass().add("stat-header");

            grid.add(label, column, 0);

            GridPane.setHalignment(
                label,
                HPos.CENTER
            );
        }
    }
    
    private void addStatRow(
            GridPane grid,
            int row,
            Object... values) {

        for (int column = 0; column < values.length; column++) {

            Label label = new Label(
                String.valueOf(values[column])
            );

            label.getStyleClass().add("stat-cell");

            grid.add(label, column, row);

            if (column > 0) {
                GridPane.setHalignment(
                    label,
                    HPos.CENTER
                );
            }
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
        GridPane statsGrid = addStats(unit);
        modelPanel.getChildren().add(0,statsGrid);
        
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