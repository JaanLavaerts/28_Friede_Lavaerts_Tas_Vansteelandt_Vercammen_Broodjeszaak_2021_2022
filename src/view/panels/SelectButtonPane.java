package view.panels;

import controller.BestelViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.BelegSoort;
import model.Broodje;
import model.Item;
import model.database.Service;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

public class SelectButtonPane extends VBox{
    final String IDLE_BUTTON_STYLE = "-fx-background-color: #aaa;";
    final String IDLE_BUTTON_STYLE2 = "-fx-background-color: #FFFF00;";
    final String HOVERED_BUTTON_STYLE = "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";


    private ObservableList<Broodje> broodjes;
    private ObservableList<BelegSoort> beleg;

    public SelectButtonPane(BestelViewController bestelViewController){
        HBox broodjesBox = new HBox();
        HBox belegBox = new HBox();
        this.setSpacing(20);
        broodjesBox.setSpacing(5);
        belegBox.setSpacing(5);
        this.setBackground(new Background(new BackgroundFill(Color.CYAN, new CornerRadii(5), Insets.EMPTY)));
        this.setPadding(new Insets(10));

        refresh();

        for (Broodje brood:
             broodjes) {
            Button b = new Button(brood.getBeschrijving());
            b.setOnAction((event) -> bestelViewController.toevoegenBroodje(brood.getBeschrijving()));
            b.setBackground(new Background(new BackgroundFill(Color.GRAY, new CornerRadii(5), Insets.EMPTY)));
            b.setMinWidth(10);
            b.setStyle(IDLE_BUTTON_STYLE);
            b.setCursor(Cursor.HAND);
            b.setOnMouseEntered(e -> b.setStyle(HOVERED_BUTTON_STYLE));
            b.setOnMouseExited(e -> b.setStyle(IDLE_BUTTON_STYLE));
            broodjesBox.getChildren().add(b);
        }

        for (BelegSoort belegSoort:
                beleg) {
            Button b = new Button(belegSoort.getBeschrijving());
            b.setStyle(IDLE_BUTTON_STYLE2);
            b.setCursor(Cursor.HAND);
            b.setOnMouseEntered(e -> b.setStyle(HOVERED_BUTTON_STYLE));
            b.setOnMouseExited(e -> b.setStyle(IDLE_BUTTON_STYLE2));
            b.setMinWidth(10);
            b.setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(5), Insets.EMPTY)));
            belegBox.getChildren().add(b);
        }

        this.getChildren().addAll(broodjesBox, belegBox);
    }

    public void refresh(){
        broodjes = FXCollections.observableArrayList((Collection<? extends Broodje>) Service.getInstance().getBroodjesService().getAll().stream()
                .filter(b -> b.getInstock() > 0)
                .sorted(Comparator.comparing(Broodje::getBeschrijving))
                .collect(Collectors.toCollection(ArrayList::new)));
        beleg = FXCollections.observableArrayList((Collection<? extends BelegSoort>) Service.getInstance().getBelegService().getAll().stream()
                .filter(b -> b.getInstock() > 0)
                .sorted(Comparator.comparing(BelegSoort::getBeschrijving))
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    public void updateStatusBroodjesKnoppen(HashMap<String, Broodje> broodjeHashMap){
        broodjes = FXCollections.observableArrayList((Collection<? extends Broodje>) broodjeHashMap.values().stream()
                .map(b -> (Broodje) b)
                .sorted(Comparator.comparing(Broodje::getBeschrijving))
                .collect(Collectors.toCollection(ArrayList::new)));
    }

}
