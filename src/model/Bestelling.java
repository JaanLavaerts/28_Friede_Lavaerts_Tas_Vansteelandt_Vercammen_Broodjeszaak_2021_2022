package model;

import java.util.ArrayList;

public class Bestelling {

    //vars
    private ArrayList<Broodje> bestelling;
    //private BestellingState state;

    //constructor,   public?
    public Bestelling() {
        this.bestelling = new ArrayList<>();
    }

    //getter
    public ArrayList<Broodje> getBestelling() {
        return (ArrayList<Broodje>) this.bestelling.clone();
    }

    //adder
    public void addBroodje(Broodje b) {
        this.bestelling.add(b);
    }

    //remover
    public void removeBroodje(Broodje b) {
        this.bestelling.remove(b);
    }



}
