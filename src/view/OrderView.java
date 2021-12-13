package view;

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


public class OrderView {

	private Stage stage = new Stage();
		
	public OrderView(){			
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
		root.getChildren().add(gridPane);

		HBox newOrderHBox = new HBox();
		newOrderHBox.setSpacing(10);
		newOrderHBox.setAlignment(Pos.TOP_LEFT);
		Button newOrderButton = new Button("Nieuwe Bestelling");
		newOrderHBox.getChildren().addAll(newOrderButton);


		Label SerialNumberLabel = new Label("Volgnr: ");
		newOrderHBox.getChildren().addAll(SerialNumberLabel);

		HBox choiceBoxHBox = new HBox();
		ChoiceBox<String> choiceBox = new ChoiceBox();
		choiceBoxHBox.setSpacing(10);
		choiceBoxHBox.setAlignment(Pos.TOP_RIGHT);
		choiceBoxHBox.getChildren().addAll(choiceBox);

		choiceBox.getItems().addAll("Goodkoopste broodje gratis", "2", "3", "4", "5", "6", "7", "8", "9", "10");
		choiceBox.setValue("Goodkoopste broodje gratis");

		gridPane.add(newOrderHBox, 0, 0, 2, 1);
		gridPane.add(choiceBoxHBox, 4, 0);

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
}
