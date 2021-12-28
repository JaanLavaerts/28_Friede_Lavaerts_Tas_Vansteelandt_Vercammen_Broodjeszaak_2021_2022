package view;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.BestelFacade;
import model.Item;
import model.database.Service;


public class AdminStatistiekPane extends VBox {

    ObservableList<Item> items;

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final StackedBarChart<String, Number> sbc = new StackedBarChart<String, Number>(xAxis, yAxis);


    public AdminStatistiekPane(){

        sbc.setTitle("Statistieken");
        xAxis.setLabel("Producten");
        sbc.setAnimated(false);
        refresh();
        ArrayList<String> names = items.stream().map(Item::getBeschrijving).collect(Collectors.toCollection(ArrayList::new));
        xAxis.setCategories(FXCollections.observableArrayList(names));
        yAxis.setLabel("Aantal verkocht");
        this.getChildren().add(sbc);
    }

    public void refresh(){
        items = FXCollections.observableArrayList(Service.getInstance().getBroodjesService().getAll());
        items.addAll(FXCollections.observableArrayList(Service.getInstance().getBelegService().getAll()));
        sbc.getData().clear();
        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
        for (Item i:
                items) {
            series1.getData().add(new XYChart.Data<String, Number>(i.getBeschrijving(), i.getVerkocht()));
        }
        sbc.getData().addAll(series1);
    }

}
