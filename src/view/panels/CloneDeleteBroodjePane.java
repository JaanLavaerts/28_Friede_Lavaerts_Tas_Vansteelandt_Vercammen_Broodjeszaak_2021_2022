package view.panels;

import controller.BestelViewController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CloneDeleteBroodjePane extends VBox {

    private BestelViewController controller;

    public CloneDeleteBroodjePane(BestelViewController controller){
        this.controller = controller;
        Label title = new Label("Selecteer lijn in lijst");
        Button addSameBroodje = new Button("Voeg zelfde broodje toe");
        Button removeBroodje = new Button("Verwijder broodje");
        this.setBackground(new Background(new BackgroundFill(Color.CYAN, new CornerRadii(5), Insets.EMPTY)));

        addSameBroodje.setOnAction(event -> controller.addSameBroodje());
        removeBroodje.setOnAction(event -> controller.removeBroodje());

        this.getChildren().addAll(title, addSameBroodje, removeBroodje);
    }
}
