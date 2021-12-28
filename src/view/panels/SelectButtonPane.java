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
import model.BelegSoort;
import model.database.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

public class SelectButtonPane extends VBox {
    final String IDLE_BUTTON_STYLE = "-fx-background-color: #aaa;";
    final String IDLE_BUTTON_STYLE2 = "-fx-background-color: #FFFF00;";
    final String HOVERED_BUTTON_STYLE = "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";


    private ObservableList<Broodje> broodjes;
    private ObservableList<BelegSoort> beleg;
    private ArrayList<Button> buttons = new ArrayList<>();
    private HBox broodjesBox;
    private BestelViewController bestelViewController;

    public SelectButtonPane(BestelViewController bestelViewController) {
        this.bestelViewController = bestelViewController;
        broodjesBox = new HBox();
        HBox belegBox = new HBox();
        this.setSpacing(20);
        broodjesBox.setSpacing(5);
        belegBox.setSpacing(5);
        this.setBackground(new Background(new BackgroundFill(Color.CYAN, new CornerRadii(5), Insets.EMPTY)));
        this.setPadding(new Insets(10));

        refresh();

        for (Broodje brood :
                broodjes) {
            Button b = makeButtonBrood(brood.getBeschrijving());
            broodjesBox.getChildren().add(b);
            buttons.add(b);
        }

        for (BelegSoort belegSoort:
                beleg) {
            Button b = makeButtonBeleg(belegSoort.getBeschrijving());
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

    public void updateStatusBroodjesKnoppen(HashMap<String, Broodje> broodjeHashMap) {
        broodjesBox.getChildren().clear();
        ArrayList<Broodje> values = broodjeHashMap.values().stream().sorted(Comparator.comparing(Broodje::getBeschrijving)).filter(b -> b.getInstock() > 0).collect(Collectors.toCollection(ArrayList::new));
        for (Broodje value : values) {
            Button b = makeButtonBrood(value.getBeschrijving());
            broodjesBox.getChildren().add(b);
        }
    }

    private Button makeButtonBrood(String name) {
        Button b = makeBasicButton(name);
        b.setOnAction((event) -> bestelViewController.toevoegenBroodje(name));
        b.setStyle(IDLE_BUTTON_STYLE);
        b.setOnMouseEntered(e -> b.setStyle(HOVERED_BUTTON_STYLE));
        b.setOnMouseExited(e -> b.setStyle(IDLE_BUTTON_STYLE));
        return b;
    }

    private Button makeButtonBeleg(String name) {
        Button b = makeBasicButton(name);
        b.setStyle(IDLE_BUTTON_STYLE2);
        b.setOnMouseEntered(e -> b.setStyle(HOVERED_BUTTON_STYLE));
        b.setOnMouseExited(e -> b.setStyle(IDLE_BUTTON_STYLE2));
        return b;
    }

    private Button makeBasicButton(String name) {
        Button b = new Button(name);
        b.setCursor(Cursor.HAND);
        b.setMinWidth(10);
        b.setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(5), Insets.EMPTY)));
        return b;
    }

}
