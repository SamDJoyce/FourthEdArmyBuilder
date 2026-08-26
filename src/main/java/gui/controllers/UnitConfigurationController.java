package gui.controllers;

import builder.ArmyBuilder;
import gui.EditText;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import units.instances.UnitInstance;
import units.options.OptionGroup;

public class UnitConfigurationController {

    @FXML
    private Label unitNameLabel;

    @FXML
    private VBox optionGroupsPanel;

    private ArmyBuilder armyBuilder;

    private UnitInstance unit;
    
    private EditText change = new EditText();

    public void setArmyBuilder(ArmyBuilder armyBuilder) {
        this.armyBuilder = armyBuilder;
    }

    public void setUnit(UnitInstance unit) {
        this.unit = unit;
        refresh();
    }

    public void refresh() {

        if (unit == null) {
            return;
        }

        unitNameLabel.setText(change.toTitleCase(
                unit.getDescription().getName())
        );

        optionGroupsPanel.getChildren().clear();

        for (OptionGroup group :
                unit.getDescription().getOptions()) {

            addOptionGroupToPanel(group);
        }
    }

    private void addOptionGroupToPanel(OptionGroup group) {

        Label label = new Label(change.toTitleCase(
        						change.removeGroupTag(
        								group.getName()
        						)));

        optionGroupsPanel.getChildren().add(label);
    }
    
}