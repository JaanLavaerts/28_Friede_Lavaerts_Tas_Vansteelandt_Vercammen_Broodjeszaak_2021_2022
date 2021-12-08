package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.database.LoadSaveStrategies.LoadSaveStrategyEnum;
import model.database.Service;
import view.AdminView;
import view.KitchenView;
import view.OrderView;


public class BroodjeszaakMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		Service.getInstance().getBelegService().setLoadSaveStrategyEnum(LoadSaveStrategyEnum.EXCELBELEG);
		Service.getInstance().getBroodjesService().setLoadSaveStrategyEnum(LoadSaveStrategyEnum.TEKSTBROODJE);
		AdminView adminView = new AdminView();
		OrderView orderView = new OrderView();
		KitchenView kitchenView = new KitchenView();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
