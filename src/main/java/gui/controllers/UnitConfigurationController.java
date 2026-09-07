package gui.controllers;

import java.util.ArrayList;
import java.util.Comparator;

import builder.ArmyBuilder;
import gui.FormatText;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.VBox;
import roster.ValidationResult;
import units.ModelFactory;
import units.instances.ModelInstance;
import units.instances.UnitInstance;
import units.options.OptionChoice;
import units.options.OptionChoiceFactory;
import units.options.OptionGroup;
import units.options.OptionOwner;
import units.options.SelectionContext;

public class UnitConfigurationController<T> {

    @FXML
    private Label unitNameLabel;

    @FXML
    private VBox optionGroupsPanel;

    private ArmyBuilder armyBuilder;
    
    private OptionOwner owner;
    
    private FormatText change = new FormatText();
    
    private Runnable rosterRefresh;

    public void setArmyBuilder(ArmyBuilder armyBuilder) {
        this.armyBuilder = armyBuilder;
    }
    
    public void setOwner(OptionOwner owner) {
    	this.owner = owner;
    	refresh();
    }

    public void refresh() {
    
        if (owner == null) {
            return;
        }
        
        unitNameLabel.setText(change.toTitleCase(
                owner.getName())
        );

       populateGroups();
       rosterRefresh.run();
    }
    
    private void populateGroups() {
        optionGroupsPanel.getChildren().clear();
        
        // Add model count control
        if (owner.isUnit()){
        	UnitInstance unit = (UnitInstance) owner;
        	if (unit.getDescription().getModelToAdd() 
        			!= null
        	&&  unit.getDescription().getMinSize() 
        			!= unit.getDescription().getMaxSize()) {
        		addModelCountSpinner(unit);
        	}
        }
        
        ArrayList<OptionGroup> orderedGroups = new ArrayList<>(owner.getOptions());
	        orderedGroups.sort(Comparator.comparing(
					OptionGroup::getName,
					String.CASE_INSENSITIVE_ORDER
			));
        										
        for (OptionGroup group : orderedGroups) {
        	
        	SelectionContext context = SelectionContext.forGroup(owner, group);
        	
            if (canSelect(context, group)
            ||  hasSelectionInGroup(owner, group)) {

                populateOptionGroup(
                        group,
                        new VBox());
            }
        }
    }
    

    private void populateOptionGroup(
    		OptionGroup group,
    		VBox panel) {
    	// Create Title
        String groupName = change.toTitleCase(
                change.removeGroupTag(group.getName()));

        panel = new VBox();

        Label label = new Label(groupName);
        panel.getChildren().add(label);

        ArrayList<OptionChoice> orderedChoices =
                new ArrayList<>(group.getChoices());

        orderedChoices.sort(Comparator.comparing(
                OptionChoice::getName,
                String.CASE_INSENSITIVE_ORDER
        ));
        
        for (OptionChoice choice : orderedChoices) {

            SelectionContext context =
                    SelectionContext.create(owner, choice);
            
            if (canSelect(context, choice)
            ||  owner.hasSelection(choice)) {
                addChoiceRadioButton(choice, panel);
            }
        }

        optionGroupsPanel.getChildren().add(panel);
    }
    
    private void addModelCountSpinner(UnitInstance unit) {

        Label label = new Label("Models");

        int currentCount = unit.getModels().size();

        int minCount = unit.getDescription().getMinSize();
        int maxCount = unit.getDescription().getMaxSize();
        

        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        minCount,
                        maxCount,
                        currentCount
                );

        Spinner<Integer> modelCount = new Spinner<>();
        modelCount.setValueFactory(valueFactory);

        modelCount.valueProperty().addListener(
                (obs, oldValue, newValue) -> {

                    if (newValue > oldValue) {

                        addModelToUnit(unit);

                    } else if (newValue < oldValue) {

                        removeModelFromUnit(unit);
                    }
                }
        );

        VBox modelCountPanel = new VBox(5);
        modelCountPanel.getChildren().addAll(
                label,
                modelCount
        );

        optionGroupsPanel.getChildren().add(modelCountPanel);
    }
    
    private void addModelToUnit(UnitInstance unit) {

        ModelInstance model = ModelFactory.getInstance(
        						unit.createModel()) ;

        ValidationResult result =
                armyBuilder.addModel(unit, model);

        if (!result.isValid()) {
            System.out.println(
                    "Cannot add model: " + result.getMessage()
            );
        }
        refresh();
    }
    
    private void removeModelFromUnit(UnitInstance unit) {

        if (unit.getModels().isEmpty()) {
            return;
        }

        ModelInstance model =
                unit.getModels().get(unit.getModels().size() - 1);

        ValidationResult result =
                armyBuilder.removeModel(model);

        if (!result.isValid()) {
            System.out.println(
                    "Cannot remove model: " + result.getMessage()
            );
        }

        refresh();
    }
    
    private void addChoiceRadioButton(
            OptionChoice choice,
            VBox panel) {

        RadioButton radioButton = new RadioButton(
                change.toTitleCase(
                        change.removeChoiceTag(
                                choice.getName()))
        );

        radioButton.setMaxWidth(Double.MAX_VALUE);
        radioButton.getStyleClass().add("choice-button");

        radioButton.setSelected(owner.hasSelection(choice));

        radioButton.setOnAction(event -> {

            ValidationResult result;

            if (radioButton.isSelected()) {

                result = armyBuilder.selectOption(owner, choice);

                if (result.isValid()) {
                    System.out.println(
                            "Selected Choice: " + choice.getName());
                } else {
                    radioButton.setSelected(false);
                }

            } else {

                result = armyBuilder.removeOption(owner, choice);

                if (result.isValid()) {
                    System.out.println(
                            "Removed Choice: " + choice.getName());
                } else {
                    radioButton.setSelected(true);
                }
            }
            refresh();
        });

        panel.getChildren().add(radioButton);
    }
    
    public void setRosterRefresh(Runnable rosterRefresh) {
        this.rosterRefresh = rosterRefresh;
    } 
    
    public boolean canSelect(
    		SelectionContext context,
    		OptionChoice choice) {
    	ValidationResult result = choice.checkRequirements(context);
    	if (result.isValid()
        && !owner.hasSelection(choice) ) {
    		return true;
    	}
    	return false;
    }
    
    public boolean canSelect(
    		SelectionContext context,
    		OptionGroup group) {
    	ValidationResult result = group.checkRequirements(context);
    	if (result.isValid()) {
    	    		return true;
    	    	}
    	return false;
    }
    
    private boolean hasSelectionInGroup(
            OptionOwner owner,
            OptionGroup group) {
        
        for (OptionChoice choice : group.getChoices()) {
            if (owner.hasSelection(choice)) {
                return true;
            }
        }

        return false;
    }
}