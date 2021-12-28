package application;
	
import controller.AdminViewController;
import controller.BestelViewController;
import controller.KitchenViewController;
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
		Service.getInstance();
		BestelFacade bestelFacade = new BestelFacade();
		BestelViewController bestelViewController = new BestelViewController(bestelFacade);
		KitchenViewController kitchenViewController = new KitchenViewController(bestelFacade);
		AdminViewController adminViewController = new AdminViewController(bestelFacade);
		AdminView adminView = new AdminView(adminViewController);
		OrderView orderView = new OrderView(bestelViewController);
		KitchenView kitchenView = new KitchenView(kitchenViewController);
		bestelViewController.setOrderView(orderView);
		bestelViewController.setAdminView(adminView);
		kitchenViewController.setKitchenView(kitchenView);
		adminViewController.setAdminView(adminView);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
