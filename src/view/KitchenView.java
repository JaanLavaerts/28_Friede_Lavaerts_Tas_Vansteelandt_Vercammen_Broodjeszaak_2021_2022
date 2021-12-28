package view;

import controller.KitchenViewController;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Bestelling;

public class KitchenView {
	
	private Stage stage = new Stage();
	private Label aantalBestellinenLabel;
	private Button bestellingAfrondenButton;
	private Button volgendeBestellingButton;
	private KitchenViewController controller;
	private Label aantalBroodjes;
	private Label volgnummer;
	private Label orderLabel;

	public KitchenView(KitchenViewController controller){
		this.controller = controller;
		stage.setTitle("KITCHEN VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(680);
		stage.setY(470);
		Group root = new Group();
		VBox vBox = new VBox(20);
		vBox.setPadding(new javafx.geometry.Insets(10));
		HBox hBox = new HBox(20);
		hBox.setPadding(new Insets(10));
		Scene scene = new Scene(root, 650, 200);			
		stage.setScene(scene);
		stage.sizeToScene();


		bestellingAfrondenButton = new Button("Bestelling afronden");
		volgendeBestellingButton = new Button("Volgende bestelling");
		bestellingAfrondenButton.setDisable(true);
		volgendeBestellingButton.setDisable(true);
		HBox aantalbroodjesBox = new HBox();
		aantalBestellinenLabel = new Label("Aantal bestellingen: ");
		aantalBroodjes = new Label("Aantal broodjes: ");
		volgnummer = new Label("Aantal broodjes: ");
		orderLabel = new Label("");

		aantalbroodjesBox.getChildren().addAll(volgnummer, aantalBroodjes);

		bestellingAfrondenButton.setOnAction(action -> controller.bestellingAfronden());
		volgendeBestellingButton.setOnAction(action -> controller.volgendeBestelling());

		hBox.getChildren().addAll(bestellingAfrondenButton,volgendeBestellingButton);
		vBox.getChildren().addAll(aantalBestellinenLabel, aantalbroodjesBox, orderLabel, hBox);
		root.getChildren().add(vBox);
		stage.show();

	}

	public void updateInQueue(int amount){
		aantalBestellinenLabel.setText("Aantal bestellingen: " + amount);
	}

	public void setAmountBroodjes(int amount){
		aantalBroodjes.setText("Aantal broodjes: "+amount);
	}

	public void setBestelNumber(int number){
		volgnummer.setText("Volgnummer bestelling: "+number +" - ");

	}

	public void setOrder(String order){
		orderLabel.setText(order);

	}

	public void refresh(){
		stage.show();
	}

	public void setEnableVolgendeBestelling(){
		volgendeBestellingButton.setDisable(false);

	}
	public void setDisableVolgendeBestelling(){
		volgendeBestellingButton.setDisable(true);
	}

	public void setDisableBestellingafronden(){
		bestellingAfrondenButton.setDisable(true);
	}
	public void setEnableBestellingafronden(){
		bestellingAfrondenButton.setDisable(false);
	}

}
