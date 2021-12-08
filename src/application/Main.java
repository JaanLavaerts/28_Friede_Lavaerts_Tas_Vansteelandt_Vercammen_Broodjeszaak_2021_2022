package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.database.Service;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			//start unofficial tests
			Service service = Service.getInstance();

			System.out.println(service.getBroodjesService().getAll());
			System.out.println(service.getBelegService().getAll());
			System.out.println(service.getBroodjesService().getStockVanSoort("wit"));
			System.out.println(service.getBroodjesService().getPrijsVanSoort("volkoren"));
			System.out.println(service.getBroodjesService().getVerkochtVanSoort("mais"));
			System.out.println(service.getBelegService().getStockVanSoort("hesp"));
			System.out.println(service.getBelegService().getPrijsVanSoort("kaas"));
			System.out.println(service.getBelegService().getVerkochtVanSoort("sla"));
			//end tests

			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			//moest dit wegdoen om te kunnen pushen, iets met een nullpointerexception
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
