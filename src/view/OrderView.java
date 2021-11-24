package view;

import javafx.scene.Group;
import javafx.scene.Scene;
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
	}
}
