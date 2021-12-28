package view;


import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import view.panels.SandwichOverviewPane;

public class AdminMainPane extends BorderPane {

    private SandwichOverviewPane sandwichOverviewPane;
    private AdminStatistiekPane adminStatistiekPane;

	public AdminMainPane(){
	    TabPane tabPane = new TabPane(); 	    
        //Tab spelVerloopTab = new Tab("Spelverloop");
        sandwichOverviewPane = new SandwichOverviewPane();
        adminStatistiekPane = new AdminStatistiekPane();
        Tab broodjesTab = new Tab("Broodjes/Beleg",sandwichOverviewPane);
        Tab instellingTab = new Tab("Instellingen");
        Tab statistiekTab = new Tab("Statistieken", adminStatistiekPane);
        //tabPane.getTabs().add(spelVerloopTab);
        tabPane.getTabs().add(broodjesTab);
        tabPane.getTabs().add(statistiekTab);
        tabPane.getTabs().add(instellingTab);
        this.setCenter(tabPane);
	}

    public void refresh(){
        sandwichOverviewPane.refresh();
        adminStatistiekPane.refresh();
    }
}
