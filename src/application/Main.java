package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import model.database.BroodjesDatabase;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BroodjesDatabase database = new BroodjesDatabase();
			database.loadBroodjes();

			//start unofficial tests
			System.out.println(database.getBroodjes());
			System.out.println(database.getBeleg());
			System.out.println(database.getBroodStock("wit"));
			System.out.println(database.getBroodPrijs("volkoren"));
			System.out.println(database.getBroodVerkocht("mais"));
			System.out.println(database.getBelegStock("hesp"));
			System.out.println(database.getBelegPrijs("kaas"));
			System.out.println(database.getBelegVerkocht("sla"));
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
