package gui.controllers;

import builder.ArmyBuilder;
import gui.FormatText;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
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

    //private UnitInstance unit;
    
    //private ModelInstance model;
    
    private FormatText change = new FormatText();
    
    private Runnable rosterRefresh;

    public void setArmyBuilder(ArmyBuilder armyBuilder) {
        this.armyBuilder = armyBuilder;
    }

//    public void setUnit(UnitInstance unit) {
//        this.unit = unit;
//        refresh();
//        
//    }
    
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

        optionGroupsPanel.getChildren().clear();

        for (OptionGroup group : owner.getOptions()) {
            addOptionGroupToPanel(group);
        }
        
    }

    private void addOptionGroupToPanel(OptionGroup group) {

    	String labelText = change.toTitleCase(
							change.removeGroupTag(
							 group.getName()));
    	
    	for (OptionChoice o : group.getChoices()) {
    		labelText += String.format(
    				"\n     - %s", 
    				change.removeChoiceTag(o.getName()));
    	}
    	
        Label label = new Label(labelText);

        optionGroupsPanel.getChildren().add(label);
    }
    
    public void setRosterRefresh(Runnable rosterRefresh) {
        this.rosterRefresh = rosterRefresh;
    } 
}