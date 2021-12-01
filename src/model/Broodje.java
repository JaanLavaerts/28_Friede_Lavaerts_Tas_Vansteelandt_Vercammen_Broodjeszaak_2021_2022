package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Broodje {

    //vars
    private Brood broodsoort;
    private HashMap<Beleg, Integer> beleg;



    //lege constructor
    public Broodje() {
        this.broodsoort = new Brood("Geen broood", 0,1,0);
        this.beleg = new HashMap<Beleg, Integer>();
    }

    //constructor met alles al meegegeven
    public Broodje(Brood broodsoort,ArrayList<Beleg> beleg) {
        this.broodsoort = broodsoort;
        this.beleg = (HashMap<Beleg, Integer>) beleg.clone();
    }



    //setter
    public void setBroodsoort(Brood soort) {
        this.broodsoort = soort;
    }

    //getter
    public Brood getBroodsoort() {
        return this.broodsoort;
    }

    //adder
    public void addBeleg(Beleg beleg) {
        addBeleg(beleg, 1);
    }

    //adder met aantal
    public void addBeleg(Beleg beleg, int aantal) {
        int amount = getBelegAmount(beleg) + aantal;
        this.beleg.put(beleg, amount);
    }

    public int getBelegAmount(Beleg beleg) {
        if (this.beleg.get(beleg) == null) {
            return 0;
        }
        else {
            return this.beleg.get(beleg);
        }
    }

    //remover
    public void removeBeleg(Beleg beleg) {
        this.beleg.remove(beleg);
    }

    //remover met aantal te removen
    public void removeBeleg(Beleg beleg, int aantal) {
        addBeleg(beleg, -aantal);
        if (this.beleg.get(beleg) < 1) {
            this.beleg.remove(beleg);
        }
    }

    //getter
    public HashMap<Beleg, Integer> getBeleg() {
        return (HashMap<Beleg, Integer>) this.beleg.clone();
    }
}
