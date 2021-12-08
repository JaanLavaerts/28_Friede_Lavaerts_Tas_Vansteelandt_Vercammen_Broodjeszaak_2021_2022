package view.panels;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Item;
import model.database.Service;


public class SandwichOverviewPane extends GridPane {

	private TableView<Item> table;
	private ObservableList<Item> items;

	public SandwichOverviewPane() {
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
		refresh();
		TableColumn<Item, String> colTitle = new TableColumn<Item, String>("soort");
		colTitle.setMinWidth(300);
		colTitle.setCellValueFactory(new PropertyValueFactory<Item, String>("beschrijving"));
		TableColumn<Item, Integer> colYear = new TableColumn<Item, Integer>("prijs");
		colYear.setMinWidth(100);
		colYear.setCellValueFactory(new PropertyValueFactory<Item, Integer>("prijs"));
		TableColumn<Item, Double> colPrice = new TableColumn<Item, Double>("stock");
		colPrice.setMinWidth(100);
		colPrice.setCellValueFactory(new PropertyValueFactory<Item, Double>("instock"));
		table.getColumns().addAll(colTitle, colYear, colPrice);

		this.getChildren().addAll(lblHeading, table);
	}

	public void displayMessage(String message){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Informatie ");
		alert.setContentText(message);
		alert.show();
	}

	public void refresh(){
		items = FXCollections.observableArrayList(Service.getInstance().getBelegService().getAll());
		items.addAll(FXCollections.observableArrayList(Service.getInstance().getBroodjesService().getAll()));
		table.setItems(items);
		table.refresh();
	}
}
