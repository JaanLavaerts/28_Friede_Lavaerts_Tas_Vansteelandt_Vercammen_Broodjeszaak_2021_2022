package view;

import controller.AdminViewController;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.kortingen.KortingEnum;

public class AdminSettingsPane  extends VBox {

    ChoiceBox<String> belegDatabaseChoice;
    ChoiceBox<String> broodjesDatabaseChoice;
    ChoiceBox<String> kortingChoice;
    AdminViewController controller;
    Label infoLabel;

    public AdminSettingsPane(AdminViewController controller){
        this.controller = controller;
        belegDatabaseChoice = new ChoiceBox<>();
        broodjesDatabaseChoice = new ChoiceBox<>();
        kortingChoice = new ChoiceBox<>();
        infoLabel = new Label("Kies de database/korting settings: ");

        belegDatabaseChoice.getItems().addAll("beleg.txt", "beleg.xls");
        broodjesDatabaseChoice.getItems().addAll("broodjes.txt", "broodjes.xls");
        kortingChoice.getItems().addAll(KortingEnum.GoedkoopsteGratisKorting.NAME, KortingEnum.TienProcentKorting.NAME, KortingEnum.GeenKorting.NAME);

        Button save = new Button("save");
        save.setOnAction(event -> controller.saveSettings());


        this.getChildren().addAll(infoLabel, belegDatabaseChoice, broodjesDatabaseChoice, kortingChoice, save);

    }
    public void setSettings(String broodjesdatabase,String belegdatabase,String kortingchoice){
            belegDatabaseChoice.setValue(belegdatabase);
            broodjesDatabaseChoice.setValue(broodjesdatabase);
            kortingChoice.setValue(kortingchoice);
    }

    public String getBroodjeDatabaseChoice(){
        return broodjesDatabaseChoice.getValue();
    }

    public String getKortingChoice(){
        return kortingChoice.getValue();
    }

    public String getBelegDatabaseChoice(){
        return belegDatabaseChoice.getValue();
    }


}
