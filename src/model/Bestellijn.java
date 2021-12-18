package model;

import model.database.Service;

public class Bestellijn {

    private Broodje broodje;
    private String naamBroodje;

    public Bestellijn(Broodje broodje){
        this.broodje = broodje;
        this.naamBroodje = broodje.getBeschrijving();
        Service.getInstance().getBroodjesService().aanpassenVoorraad(naamBroodje);
    }

}
