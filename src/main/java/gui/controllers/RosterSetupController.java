package gui.controllers;

import java.util.List;
import java.util.function.Consumer;

import forceOrg.ForceOrgChart;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import loaders.CodexLoader;
import roster.Codex;

public class RosterSetupController {

    @FXML
    private TextField nameField;

    @FXML
    private Spinner<Integer> pointsSpinner;

    @FXML
    private ComboBox<Codex> codexComboBox;
    
    @FXML
    private ComboBox<ForceOrgChart> forceOrgComboBox;

    private Consumer<RosterSetup> onCreate;

    public void setCodexes(List<Codex> codexes) {
        codexComboBox.getItems().setAll(codexes);

        if (!codexes.isEmpty()) {
            codexComboBox.getSelectionModel().selectFirst();
        }
    }
    
    public void setForceOrgCharts(List<ForceOrgChart> charts) {
        forceOrgComboBox.getItems().setAll(charts);

        if (!charts.isEmpty()) {
            forceOrgComboBox.getSelectionModel().selectFirst();
        }
    }

    public void setOnCreate(Consumer<RosterSetup> onCreate) {
        this.onCreate = onCreate;
    }

    @FXML
    private void initialize() {
        pointsSpinner.setValueFactory(
            new SpinnerValueFactory.IntegerSpinnerValueFactory(
                1,
                10000,
                1000
            )
        );
    }

    @FXML
    private void createRoster() {
        String name = nameField.getText().trim();
        int pointsLimit = pointsSpinner.getValue();
        ForceOrgChart chart =
            forceOrgComboBox.getSelectionModel().getSelectedItem();
        Codex codex =
        	    codexComboBox.getSelectionModel().getSelectedItem();

        if (name.isEmpty()) {
            return;
        }

        if (chart == null) {
            return;
        }
        
        if (codex == null) {
        	return;
        }

        if (onCreate != null) {
            onCreate.accept(
                new RosterSetup(
                    name,
                    pointsLimit,
                    codex,
                    chart
                )
            );
        }
    }
}