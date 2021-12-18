package model;

import model.database.Service;

import java.util.ArrayList;
import java.util.HashMap;

public class BestelFacade implements Subject  {

    HashMap<BestellingEvents, ArrayList<Observer>> observers = new HashMap<>();
    Bestelling bestelling;

    public void toevoegenBroodje(String name){
        Broodje broodje = Service.getInstance().getBroodjesService().getBroodjeByName(name);
        bestelling = new Bestelling();
        bestelling.addBroodje(broodje);
        notifyObserver(BestellingEvents.TOEVOEGEN_BROODJE);
    }

    public ArrayList<Bestellijn> getLijstBestellijnen(){
        return bestelling.getLijstBestellijnen();
    }

    public HashMap<String, Broodje> getVoorraadlijstBroodjes(){
        return Service.getInstance().getBroodjesService().getVoorraadlijstBroodjes();
    }

    @Override
    public void addObserver(Observer observer, BestellingEvents bestellingEvents) {
        if(!observers.containsKey(bestellingEvents)){
            observers.put(bestellingEvents, new ArrayList<>());
        }
        observers.get(bestellingEvents).add(observer);
    }

    @Override
    public void removeObserver(Observer observer, BestellingEvents bestellingEvents) {
        observers.get(bestellingEvents).remove(observer);
    }

    @Override
    public void notifyObserver(BestellingEvents bestellingEvents) {
        for(Observer o: observers.get(bestellingEvents)){
            o.update(bestelling,bestellingEvents);
        }
    }
}
