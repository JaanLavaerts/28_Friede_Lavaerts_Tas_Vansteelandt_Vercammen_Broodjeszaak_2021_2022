package view;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;	

public class AdminView {
	private Stage stage = new Stage();
	private AdminMainPane adminMainPane;

	public AdminView(){			
		stage.setTitle("ADMIN VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(680);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 650, 400);
		adminMainPane = new AdminMainPane();
		adminMainPane.prefHeightProperty().bind(scene.heightProperty());
		adminMainPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(adminMainPane);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	public void refresh(){
		adminMainPane.refresh();

	}
}
