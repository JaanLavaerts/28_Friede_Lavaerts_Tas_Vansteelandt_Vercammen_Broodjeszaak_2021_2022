package model;

import java.util.ArrayList;

public class Broodje {

    //vars
    private String broodsoort;
    private ArrayList<String> beleg = new ArrayList<String>();
    /* 'Beleg' kan ook een abstracte klasse worden?
        Elk beleg is dan een klasse die 'Beleg' extend.
        Ik weet wel niet of dat het alleen meer werk maakt dus ben met 'String' gegaan voor nu.
        Zelfde voor 'broodsoort'.*/



    //lege constructor
    public Broodje() {
        this.broodsoort = "no bread whatsoever (gluten free)";
        this.beleg = new ArrayList<String>();
    }

    //constructor met alles al meegegeven
    public Broodje(String broodsoort,ArrayList<String> beleg) {
        this.broodsoort = broodsoort;
        this.beleg = (ArrayList<String>) beleg.clone();
    }



    //setter
    public void setBroodsoort(String soort) {
        this.broodsoort = soort;
    }

    //getter
    public String getBroodsoort() {
        return this.broodsoort;
    }

    //adder
    public void addBeleg(String beleg) {
        this.beleg.add(beleg);
    }

    //adder met aantal
    public void addBeleg(String beleg, int aantal) {
        for (int i = 0; i< aantal; i++) {
            this.beleg.add(beleg);
        }
    }

    //remover
    public void removeBeleg(String beleg) {
        this.beleg.remove(beleg);
    }

    //remover met aantal te removen
    public void removeBeleg(String beleg, int aantal) {
        for (int i=0; i < aantal; i++) {
            //breaks loop if no more 'beleg' to remove :)
            if (!this.beleg.remove(beleg)) {
                i = aantal;
            }
        }
    }

    //getter
    public ArrayList<String> getBeleg() {
        return (ArrayList<String>) this.beleg.clone();
    }
}
