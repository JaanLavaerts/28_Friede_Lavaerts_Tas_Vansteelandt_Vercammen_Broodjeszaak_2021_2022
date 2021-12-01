package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Bestelling {

    //vars
    private HashMap<Broodje, Integer> bestelling;
    //private BestellingState state;

    //constructor,   public?
    public Bestelling() {
        this.bestelling = new HashMap<Broodje, Integer>();
    }

    //getter
    public HashMap<Broodje, Integer> getBestelling() {
        return (HashMap<Broodje, Integer>) this.bestelling.clone();
    }

    //adder
    public void addBroodje(Broodje b, int aantal) {
        int amount = getBroodjeAmount(b) + aantal;
        this.bestelling.put(b, amount);
    }

    //adder
    public void addBroodje(Broodje b) {
        addBroodje(b, 1);
    }

    //getter
    public int getBroodjeAmount(Broodje b) {
        if (this.bestelling.get(b) != null) {
            return 0;
        }
        return this.bestelling.get(b);
    }

    //remover
    public void removeBroodje(Broodje b, int aantal) {
        addBroodje(b, -aantal);
        if (getBroodjeAmount(b) < 1) {
            this.bestelling.remove(b);
        }
    }



}
