package application;
	
import controller.BestelViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.BestelFacade;
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
		BestelFacade bestelFacade = new BestelFacade();
		BestelViewController bestelViewController = new BestelViewController(bestelFacade);
		AdminView adminView = new AdminView();
		OrderView orderView = new OrderView(bestelViewController);
		KitchenView kitchenView = new KitchenView(kitchenViewController);
		bestelViewController.setOrderView(orderView);
		bestelViewController.setAdminView(adminView);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
