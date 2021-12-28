package view;
import controller.BestelViewController;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.kortingen.KortingEnum;
import view.panels.BestellijnenTabelPane;
import view.panels.CloneDeleteBroodjePane;
import view.panels.SandwichOverviewPane;
import view.panels.SelectButtonPane;

// author : Jaan Lavaerts
public class OrderView {

	private Stage stage = new Stage();
	private SelectButtonPane selectButtonPane;
	private BestelViewController bestelViewController;
		
	public OrderView(BestelViewController bestelViewController){
		this.bestelViewController = bestelViewController;
		stage.setTitle("ORDER VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(20);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 650, 650);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();

		GridPane gridPane = new GridPane();
		selectButtonPane = new SelectButtonPane(bestelViewController);
		cloneDeleteBroodjePane = new CloneDeleteBroodjePane(bestelViewController);
		root.getChildren().add(gridPane);

		HBox newOrderHBox = new HBox();
		newOrderHBox.setSpacing(10);
		newOrderHBox.setAlignment(Pos.TOP_LEFT);

		Button newOrderButton = new Button("Nieuwe Bestelling");
		Label SerialNumberLabel = new Label("Volgnr: ");
		HBox choiceBoxHBox = new HBox();
		ChoiceBox<String> choiceBox = new ChoiceBox();

		newOrderHBox.getChildren().addAll(newOrderButton, SerialNumberLabel);
		choiceBoxHBox.setSpacing(10);
		choiceBoxHBox.setAlignment(Pos.TOP_RIGHT);
		choiceBoxHBox.getChildren().addAll(choiceBox);

		choiceBox.getItems().addAll(KortingEnum.GoedkoopsteGratisKorting.NAME, KortingEnum.TienProcentKorting.NAME, KortingEnum.GeenKorting.NAME );
		choiceBox.setValue("Geen korting");

		HBox paymentHBox = new HBox();
		HBox paymentHBox2 = new HBox();
		paymentHBox.setSpacing(10);
		paymentHBox2.setSpacing(10);
		paymentHBox.setAlignment(Pos.BOTTOM_LEFT);
		paymentHBox2.setAlignment(Pos.BOTTOM_RIGHT);

		Button afsluitenBestellingButton = new Button("Aflsuiten Bestelling");
		Button payButton = new Button("Betaal");
		Button toKitchenButton = new Button("Naar Keuken");
		Label teBetalenLabel = new Label("Te betalen: XXXXXX");

		annuleerOrderButton = new Button("Annuleer Bestelling");
		annuleerOrderButton.setOnAction(event -> bestelViewController.annuleer());

		paymentHBox2.getChildren().addAll(payButton, toKitchenButton);
		paymentHBox.getChildren().addAll(afsluitenBestellingButton, teBetalenLabel);

		gridPane.add(newOrderHBox, 0, 0, 2, 1);
		gridPane.add(choiceBoxHBox, 4, 0);
		gridPane.add(paymentHBox, 0, 5, 3, 3);
		gridPane.add(paymentHBox2, 3, 5, 3, 3);
		gridPane.add(selectButtonPane, 0,1, 5,1);
		gridPane.add(new Label("Aantal Broodjes: "), 0, 2, 1, 1);
		gridPane.add(new BestellijnenTabelPane(), 0,3, 5,1);

		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));


		// Vier kolommen
		for (int i = 0; i < 4; i++) {
			ColumnConstraints column = new ColumnConstraints(100);
			gridPane.getColumnConstraints().add(column);
		}

		// Vijf (of zes?) rijen
		for (int i = 0; i < 5; i++) {
			RowConstraints row = new RowConstraints(100);
			gridPane.getRowConstraints().add(row);
		}

	}

	public SelectButtonPane getSelectButtonPane(){
		return selectButtonPane;
	}

	public void refresh(){
		stage.sizeToScene();
		stage.show();
	}
}
