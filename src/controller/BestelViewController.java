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

}
