package controller;

import model.BestelFacade;
import model.Bestelling;
import model.BestellingEvents;
import model.Observer;
import view.KitchenView;

public class KitchenViewController implements Observer {

    private KitchenView kitchenView;
    private BestelFacade facade;

    public KitchenViewController(BestelFacade facade){
        this.facade = facade;
        facade.addObserver(this, BestellingEvents.TO_KITCHEN);
    }

    public void setKitchenView(KitchenView kitchenView) {
        this.kitchenView = kitchenView;
    }

    @Override
    public void update(Bestelling bestelling, BestellingEvents bestellingEvents) {
        kitchenView.updateInQueue(facade.getAmountInQueue());
        kitchenView.setAmountBroodjes(facade.getAmountBroodjesForBestellingInQueue());
        kitchenView.setBestelNumber(facade.getVolgnumberInQueue());
        kitchenView.setOrder(facade.getKitchenOrder());
        kitchenView.refresh();
    }

    public void bestellingAfronden() {
        facade.bestellingAfronden();
    }

    public void volgendeBestelling() {
        facade.volgendeBestelling();
        facade.notifyObserver(BestellingEvents.TO_KITCHEN);
    }
}
