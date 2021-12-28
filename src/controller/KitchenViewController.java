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
        facade.addObserver(this, BestellingEvents.GETNEXTINQUEUE);
        facade.addObserver(this, BestellingEvents.AFRONDEN_BESTELLING);
    }

    public void setKitchenView(KitchenView kitchenView) {
        this.kitchenView = kitchenView;
    }

    @Override
    public void update(Bestelling bestelling, BestellingEvents bestellingEvents) {
        if(bestellingEvents == BestellingEvents.TO_KITCHEN) {
            kitchenView.updateInQueue(facade.getAmountInQueue());
            kitchenView.setEnableVolgendeBestelling();
            kitchenView.setDisableBestellingafronden();
        }
        if(bestellingEvents == BestellingEvents.GETNEXTINQUEUE) {
            kitchenView.setAmountBroodjes(facade.getAmountBroodjesForBestellingInQueue());
            kitchenView.setBestelNumber(facade.getVolgnumberInQueue());
            kitchenView.setOrder(facade.getKitchenOrder());
            kitchenView.setEnableBestellingafronden();
            kitchenView.setDisableVolgendeBestelling();
        }
        if(bestellingEvents == BestellingEvents.AFRONDEN_BESTELLING){
            kitchenView.setAmountBroodjes(0);
            kitchenView.setBestelNumber(0);
            kitchenView.setOrder("");
            kitchenView.setDisableBestellingafronden();
            if(facade.getAmountInQueue() > 0){
                kitchenView.setEnableVolgendeBestelling();
            }
        }
        kitchenView.refresh();
    }

    public void bestellingAfronden() {
        facade.bestellingAfronden();
    }

    public void volgendeBestelling() {
        facade.volgendeBestelling();
        facade.notifyObserver(BestellingEvents.GETNEXTINQUEUE);
    }
}
