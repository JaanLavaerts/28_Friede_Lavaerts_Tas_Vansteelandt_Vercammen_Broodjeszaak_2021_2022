package model.kortingen;

import model.Bestellijn;
import model.Bestelling;
import model.database.Service;
import java.util.ArrayList;

public class TienProcentKorting implements Korting{
    @Override
    public double getPrice(Bestelling bestelling) {
        double price = 0;
        ArrayList<Bestellijn> bestellijnen = bestelling.getLijstBestellijnen();
        for(Bestellijn bestellijn: bestellijnen){
            price += Service.getInstance().getBroodjesService().getPrijsVanSoort(bestellijn.getBroodje());
            String[] beleg = bestellijn.getBeleg().split(" ");
            if(beleg.length > 1) {
                for (String s : beleg) {
                    price += Service.getInstance().getBelegService().getPrijsVanSoort(s);
                }
            }
        }
        return (Math.round(price * 0.9 * 100.0) / 100.0);
    }
}
