package model;

import model.database.Service;

import java.util.ArrayList;

public class Bestellijn {

    private Broodje broodje;
    private String naamBroodje;
    private ArrayList<BelegSoort> beleg = new ArrayList<>();

    public Bestellijn(Broodje broodje){
        this.broodje = broodje;
        this.naamBroodje = broodje.getBeschrijving();
        Service.getInstance().getBroodjesService().aanpassenVoorraadBroodje(naamBroodje, -1);
    }

    public String getBroodje() {
        return naamBroodje;
    }

    public String getBeleg() {
        StringBuilder out = new StringBuilder();
        for (BelegSoort b: beleg){
            out.append(b.getBeschrijving()).append(" ");
        }
        return out.toString();
    }

    public void addBeleg(BelegSoort belegSoort) {
        Service.getInstance().getBelegService().aanpassenVoorraadBeleg(belegSoort.getBeschrijving(), -1);
        beleg.add(belegSoort);
    }
}
