package gui.controllers;

import java.util.ArrayList;
import java.util.Comparator;

import builder.ArmyBuilder;
import gui.FormatText;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import roster.ValidationResult;
import units.options.OptionChoice;
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
        
        ArrayList<OptionGroup> orderedGroups = new ArrayList<>(owner.getOptions());
	        orderedGroups.sort(Comparator.comparing(
					OptionGroup::getName,
					String.CASE_INSENSITIVE_ORDER
			));
        										
        
        for (OptionGroup group : orderedGroups) {
//        	SelectionContext context = SelectionContext.create(owner, null);
//        	ValidationResult result = group.checkRequirements(context);

    		populateOptionGroup(
        		group,
        		new VBox());

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
            // TODO Differentiate between adding models
            // and selecting other choices
            
            if (canSelect(context, choice)
            ||  owner.hasSelection(choice)) {
                addChoiceRadioButton(choice, panel);
            }
        }

        optionGroupsPanel.getChildren().add(panel);
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
    
    
//    private void addChoiceButton(
//    		OptionChoice choice,
//    		VBox panel) {
//    	Button button  = new Button(
//		    			change.toTitleCase(
//		    			change.removeChoiceTag(
//		    					choice.getName()
//		    			)));
//        button.setMaxWidth(Double.MAX_VALUE);
//        button.getStyleClass().add("choice-button");
//        
//        button .setOnAction( event -> {
//        	ValidationResult result = 
//        			armyBuilder.selectOption(owner, choice);
//        	//System.out.println("Running choice validation");
//        	if (result.isValid()) {
//        		System.out.println("Selected Choice: " + choice.getName());
//        	}
//        	rosterRefresh.run();
//        });
//        
//        panel.getChildren().add(button);
//    }
    
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
}