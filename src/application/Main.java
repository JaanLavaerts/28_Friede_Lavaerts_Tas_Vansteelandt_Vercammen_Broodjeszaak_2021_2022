package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import model.database.BelegDatabase;
import model.database.BroodjesDatabase;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			//start unofficial tests
			BroodjesDatabase brooddatabase = new BroodjesDatabase();
			BelegDatabase belegdatabase = new BelegDatabase();

			System.out.println(brooddatabase.getDatabase());
			System.out.println(belegdatabase.getDatabase());
			System.out.println(brooddatabase.getStock("wit"));
			System.out.println(brooddatabase.getPrijs("volkoren"));
			System.out.println(brooddatabase.getVerkocht("mais"));
			System.out.println(belegdatabase.getStock("hesp"));
			System.out.println(belegdatabase.getPrijs("kaas"));
			System.out.println(belegdatabase.getVerkocht("sla"));
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
