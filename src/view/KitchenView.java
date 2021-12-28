package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class KitchenView {
	
	private Stage stage = new Stage();		
	
	public KitchenView(){			
		stage.setTitle("KITCHEN VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(680);
		stage.setY(470);
		Group root = new Group();
		Scene scene = new Scene(root, 650, 200);			
		stage.setScene(scene);
		stage.sizeToScene();


		bestellingAfrondenButton = new Button("Bestelling afronden");
		volgendeBestellingButton = new Button("Volgende bestelling");
		aantalBestellinenLabel = new Label("Aantal bestellingen: ");
		aantalBroodjes = new Label("Aantal broodjes: ");
		volgnummer = new Label("Aantal broodjes: ");
		orderLabel = new Label("");

		bestellingAfrondenButton.setOnAction(action -> controller.bestellingAfronden());
		volgendeBestellingButton.setOnAction(action -> controller.volgendeBestelling());

		hBox.getChildren().addAll(bestellingAfrondenButton,volgendeBestellingButton);
		vBox.getChildren().addAll(aantalBestellinenLabel,hBox);
		root.getChildren().add(vBox);
		stage.show();

	}
}
