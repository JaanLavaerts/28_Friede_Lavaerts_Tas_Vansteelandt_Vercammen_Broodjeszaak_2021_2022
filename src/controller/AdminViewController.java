package controller;

import model.BestelFacade;
import model.Bestelling;
import model.BestellingEvents;
import model.Observer;
import view.AdminView;
import view.OrderView;

public class AdminViewController implements Observer {

    BestelFacade facade;
    AdminView adminView;

    public AdminViewController(BestelFacade facade){
        this.facade = facade;
        facade.addObserver(this, BestellingEvents.TO_KITCHEN);
    }

    public void setAdminView(AdminView adminView){
        this.adminView = adminView;
    }

    @Override
    public void update(Bestelling bestelling, BestellingEvents bestellingEvents) {
        adminView.refresh();
    }

    public void saveSettings() {
        String broodjesDatabase = adminView.getAdminMainPane().getAdminSettingsPane().getBroodjeDatabaseChoice();
        String belegDatabase = adminView.getAdminMainPane().getAdminSettingsPane().getBelegDatabaseChoice();
        String kortingChoice = adminView.getAdminMainPane().getAdminSettingsPane().getKortingChoice();
        facade.saveSettings(broodjesDatabase, belegDatabase, kortingChoice);
        adminView.getAdminMainPane().getAdminSettingsPane().setSettings(broodjesDatabase, belegDatabase, kortingChoice);
    }
}
