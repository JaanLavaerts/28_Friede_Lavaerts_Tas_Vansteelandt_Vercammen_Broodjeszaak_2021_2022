package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.database.Service;
import view.AdminView;
import view.KitchenView;
import view.OrderView;


public class BroodjeszaakMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		Service service = Service.getInstance();
		System.out.println(service.getBroodjesService().getAll());
		System.out.println(service.getBelegService().getAll());
		System.out.println(service.getBroodjesService().getPrijsVanSoort("wit"));
		System.out.println(service.getBroodjesService().getStockVanSoort("wit"));
		System.out.println(service.getBroodjesService().getVerkochtVanSoort("mais"));
		System.out.println(service.getBelegService().getStockVanSoort("hesp"));
		System.out.println(service.getBelegService().getPrijsVanSoort("kaas"));
		System.out.println(service.getBelegService().getVerkochtVanSoort("sla"));
		AdminView adminView = new AdminView();
		OrderView orderView = new OrderView();
		KitchenView kitchenView = new KitchenView();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
