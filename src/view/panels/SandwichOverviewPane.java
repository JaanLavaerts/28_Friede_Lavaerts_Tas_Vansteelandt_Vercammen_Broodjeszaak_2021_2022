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
import model.Brood;
import model.Broodje;
import model.service.BroodjesService;
import model.service.Service;


public class SandwichOverviewPane extends GridPane {

	private BroodjesService broodjesService = Service.getInstance().getBroodjesService();
	private TableView<Brood> table;
	private ObservableList<Brood> broodjes;

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
		table = new TableView<Brood>();
		refresh();
		TableColumn<Brood, String> colTitle = new TableColumn<Brood, String>("broodjes soort");
		colTitle.setMinWidth(300);
		colTitle.setCellValueFactory(new PropertyValueFactory<Brood, String>("beschrijving"));
		TableColumn<Brood, Integer> colYear = new TableColumn<Brood, Integer>("prijs");
		colYear.setMinWidth(100);
		colYear.setCellValueFactory(new PropertyValueFactory<Brood, Integer>("prijs"));
		TableColumn<Brood, Double> colPrice = new TableColumn<Brood, Double>("stock");
		colPrice.setMinWidth(100);
		colPrice.setCellValueFactory(new PropertyValueFactory<Brood, Double>("instock"));
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
		broodjes = FXCollections.observableArrayList(broodjesService.getAll());
		table.setItems(broodjes);
		table.refresh();
	}
}
