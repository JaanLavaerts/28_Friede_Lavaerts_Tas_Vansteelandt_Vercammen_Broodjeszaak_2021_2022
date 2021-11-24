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
			System.out.println((database.getBroodjes()));
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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
