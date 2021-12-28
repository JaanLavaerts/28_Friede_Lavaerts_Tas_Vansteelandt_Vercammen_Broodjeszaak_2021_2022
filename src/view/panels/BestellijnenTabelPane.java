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
import java.util.stream.Collectors;

public class BestellijnenTabelPane extends GridPane {

    private TableView<Bestellijn> table;
    private ObservableList<Bestellijn> items;
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
        table = new TableView<Bestellijn>();
        //refresh();
        TableColumn<Bestellijn, String> colBroodje = new TableColumn<Bestellijn, String>("Broodje");
        colBroodje.setMinWidth(100);
        colBroodje.setCellValueFactory(new PropertyValueFactory<Bestellijn, String>("broodje"));
        TableColumn<Bestellijn, String> colBeleg = new TableColumn<Bestellijn, String>("Beleg");
        colBeleg.setMinWidth(300);
        colBeleg.setCellValueFactory(new PropertyValueFactory<Bestellijn, String>("beleg"));
        table.getColumns().addAll(colBroodje, colBeleg);

        this.getChildren().addAll(lblHeading, table);
    }


    public void updateBestellijnen(ArrayList<Bestellijn> bestellijnen){
        this.bestellijnen = bestellijnen;
        refresh();
    }

    public void refresh(){
        ArrayList<Bestellijn> item = bestellijnen;
        items = FXCollections.observableArrayList(item);
        table.setItems(items);
        table.refresh();
    }

    public Bestellijn getCurrentBestellijn(){
        return table.getSelectionModel().getSelectedItem();
    }

    public ArrayList<Bestellijn> getBestellijnen(){
        return bestellijnen;
    }

}
