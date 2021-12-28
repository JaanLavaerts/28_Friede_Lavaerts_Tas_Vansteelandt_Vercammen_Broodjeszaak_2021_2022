package controller;

import model.BestelFacade;
import model.Bestelling;
import model.BestellingEvents;
import model.Observer;
import view.AdminView;
import view.OrderView;

public class BestelViewController implements Observer  {

        BestelFacade facade;
        OrderView orderView;
        AdminView adminView;

public BestelViewController(BestelFacade facade){
        this.facade=facade;
        facade.addObserver(this,BestellingEvents.TOEVOEGEN_BROODJE);
        }

public void toevoegenBroodje(String naam){
        facade.toevoegenBroodje(naam);
        orderView.getSelectButtonPane().updateStatusBroodjesKnoppen(facade.getVoorraadlijstBroodjes());
        }

public void setOrderView(OrderView orderView){
        this.orderView=orderView;
        }

        public void setAdminView(AdminView adminView){
        this.adminView = adminView;
        }

@Override
public void update(Bestelling bestellings,BestellingEvents bestellingEvents){
                orderView.refresh();
                adminView.refresh();
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
