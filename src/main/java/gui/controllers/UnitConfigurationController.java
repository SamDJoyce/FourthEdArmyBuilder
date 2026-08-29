package gui.controllers;

import builder.ArmyBuilder;
import gui.FormatText;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import roster.ValidationResult;
import units.options.OptionChoice;
import units.options.OptionGroup;
import units.options.OptionOwner;

public class UnitConfigurationController {

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
        
    }
    
    private void populateGroups() {
        optionGroupsPanel.getChildren().clear();

        for (OptionGroup group : owner.getOptions()) {
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
							change.removeGroupTag(
							 group.getName()));
    	panel = new VBox();
        Label label = new Label(groupName);
        panel.getChildren().add(label);

        for (OptionChoice o : group.getChoices()) {
        	addChoiceButton(o, panel);
        }
        
        optionGroupsPanel.getChildren().add(panel);
    }
    
    private void addChoiceButton(
    		OptionChoice choice,
    		VBox panel) {
    	Button button  = new Button(
    			change.toTitleCase(choice.getName()));
        button.setMaxWidth(Double.MAX_VALUE);
        button.getStyleClass().add("choice-button");
        
        button .setOnAction( event -> {
        	ValidationResult result = 
        			armyBuilder.selectOption(owner, choice);
        	if (result.isValid()) {
        		rosterRefresh.run();
        		System.out.println("Selected Choice: " + choice.getName());
        	}
        });
        
        panel.getChildren().add(button);
    }
    
    public void setRosterRefresh(Runnable rosterRefresh) {
        this.rosterRefresh = rosterRefresh;
    } 
}