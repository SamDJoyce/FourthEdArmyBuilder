package gui.views;

import javafx.scene.layout.BorderPane;

public class ABMainView extends BorderPane {

    public ABMainView() {

        setLeft(createArmyPanel());
        setCenter(createUnitPanel());

    }


    private javafx.scene.Node createArmyPanel() {

        return new javafx.scene.control.Label(
                "Army List"
        );

    }


    private javafx.scene.Node createUnitPanel() {

        return new javafx.scene.control.Label(
                "Unit Details"
        );

    }

}