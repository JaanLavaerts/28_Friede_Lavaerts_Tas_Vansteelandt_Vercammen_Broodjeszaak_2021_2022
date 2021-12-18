package view.panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Bestellijn;
import model.Item;
import model.database.Service;

import java.util.ArrayList;

public class BestellijnenTabelPane extends GridPane {

    private TableView<Item> table;
    private ObservableList<Item> items;
    private ArrayList<Bestellijn> bestellijnen = new ArrayList<>();

    public BestellijnenTabelPane() {
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10, 10, 10, 10));
        Label lblHeading = new Label("INVENTORY");
        lblHeading.setFont(new Font("Arial", 20));
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        this.add(new Label("Broodjes:"), 0, 0, 1, 1);
        table = new TableView<Item>();
        //refresh();
        TableColumn<Item, String> colBroodje = new TableColumn<Item, String>("Broodje");
        colBroodje.setMinWidth(100);
        colBroodje.setCellValueFactory(new PropertyValueFactory<Item, String>("beschrijving"));
        TableColumn<Item, Integer> colBeleg = new TableColumn<Item, Integer>("Beleg");
        colBeleg.setMinWidth(300);
        colBeleg.setCellValueFactory(new PropertyValueFactory<Item, Integer>("prijs"));
        table.getColumns().addAll(colBroodje, colBeleg);

        this.getChildren().addAll(lblHeading, table);
    }


    public void updateBestellijnen(ArrayList<Bestellijn> bestellijnen){
        this.bestellijnen = bestellijnen;

    }

    public void refresh(){
        items = FXCollections.observableArrayList(Service.getInstance().getBelegService().getAll());
        items.addAll(FXCollections.observableArrayList(Service.getInstance().getBroodjesService().getAll()));
        table.setItems(items);
        table.refresh();
    }





}
