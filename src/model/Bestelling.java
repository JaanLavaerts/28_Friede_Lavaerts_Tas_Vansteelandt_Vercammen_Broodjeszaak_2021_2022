package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Bestelling {

    private ArrayList<Bestellijn> bestelLijnen;

    public Bestelling() {
        this.bestelLijnen = new ArrayList<>();
    }

    public void getBestelling() {

    }

    public void addBroodje(Broodje broodje) {
        Bestellijn bestellijn = new Bestellijn(broodje);
        bestelLijnen.add(bestellijn);
    }

    public void removeBroodje(Broodje broodje) {

    }


    public ArrayList<Bestellijn> getLijstBestellijnen() {
        return bestelLijnen;
    }
}