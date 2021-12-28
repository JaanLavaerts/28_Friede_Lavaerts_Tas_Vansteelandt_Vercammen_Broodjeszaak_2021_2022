package controller;

import javafx.scene.control.Alert;
import model.*;
import model.kortingen.KortingEnum;
import view.AdminView;
import view.OrderView;
import java.util.ArrayList;
import java.util.Iterator;

public class BestelViewController implements Observer {

    BestelFacade facade;
    OrderView orderView;
    AdminView adminView;

    public BestelViewController(BestelFacade facade) {
        this.facade = facade;
        facade.addObserver(this, BestellingEvents.TOEVOEGEN_BROODJE);
        facade.addObserver(this, BestellingEvents.TOEVOEGEN_BELEG);
        facade.addObserver(this, BestellingEvents.AFSLUIT);
    }


    public void toevoegenBroodje(String naam) {
        facade.toevoegenBroodje(naam);
    }

    public void toevoegenBeleg(String name) {
        Bestellijn bestellijn = orderView.getBestellijnenTabelPane().getCurrentBestellijn();
        if(bestellijn == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Geen broodje geselecteerd!");
            alert.show();
            return;
        }
        facade.toevoegenBeleg(name, bestellijn);
        orderView.getSelectButtonPane().updateStatusBelegKnoppen(facade.getVoorraadlijstBeleg());
    }

    public void setOrderView(OrderView orderView) {
        this.orderView = orderView;
        orderView.setKortingChoice(facade.getSettingsKorting());
    }

    public void setAdminView(AdminView adminView) {
        this.adminView = adminView;
    }

    @Override
    public void update(Bestelling bestellings, BestellingEvents bestellingEvents) {
        orderView.setAantalbroodjesLabel(facade.getAantalBroodjes());
        orderView.getSelectButtonPane().updateStatusBroodjesKnoppen(facade.getVoorraadlijstBroodjes());
        orderView.getSelectButtonPane().updateStatusBelegKnoppen(facade.getVoorraadlijstBeleg());
        orderView.getBestellijnenTabelPane().updateBestellijnen(facade.getLijstBestellijnen());
        orderView.getBestellijnenTabelPane().refresh();
        orderView.setVolgNummerLabel(facade.volgNumber());
        orderView.refresh();
        adminView.refresh();
    }

    public void startBestelling() {
        facade.startBestelling();
        orderView.setNewOrderButtonToInactive();
        orderView.setAfsluitenBestellingButtonToActive();
        orderView.setKortingChoice(facade.getSettingsKorting());
    }

    public void afsluitenBestelling() {
        facade.afsluitenBestelling();
        orderView.setPrice(facade.getPrice(getKortingChoice()));
        orderView.setAfsluitenBestellingButtonToInactive();
        orderView.setPayButtonToActive();
    }

    public void payBestelling() {
        facade.payBestelling();
        orderView.setPayButtonToInactive();
        orderView.setToKitchenButtonToActive();
    }

    public void toKitchen() {
        facade.addBestellingToQueue();
        facade.updateSold();
        orderView.setToKitchenButtonToInactive();
        orderView.setNewOrderButtonToActive();
    }

    public String getKortingChoice(){
        return orderView.getKortingChoice();
    }

    public void addSameBroodje() {
        Bestellijn bestellijn = orderView.getBestellijnenTabelPane().getCurrentBestellijn();
        if(bestellijn == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Geen broodje geselecteerd!");
            alert.show();
            return;
        }
        facade.addSameBroodje(bestellijn);
    }

    public void removeBroodje() {
        Bestellijn bestellijn = orderView.getBestellijnenTabelPane().getCurrentBestellijn();
        facade.removeBroodje(bestellijn);
    }

    public void annuleer() {
        orderView.setNewOrderButtonToActive();
        orderView.setAfsluitenBestellingButtonToInactive();
        orderView.setPayButtonToInactive();
        facade.getCurrentBestelling().annuleren();
        ArrayList<Bestellijn> bestellijnen =  new ArrayList<Bestellijn>(orderView.getBestellijnenTabelPane().getBestellijnen());
        for (Bestellijn bestellijn:
             bestellijnen) {
            facade.removeBroodje(bestellijn);
        }
    }
}
