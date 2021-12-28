package model.kortingen;

import model.Bestellijn;
import model.Bestelling;
import model.database.Service;

import java.util.ArrayList;

public class GoedkoopsteGratisKorting implements Korting{
    @Override
    public double getPrice(Bestelling bestelling) {
        double price = 0;
        double lowestPrice = Double.POSITIVE_INFINITY;
        ArrayList<Bestellijn> bestellijnen = bestelling.getLijstBestellijnen();
        for(Bestellijn bestellijn: bestellijnen){
            double lastPrice = price;
            price += Service.getInstance().getBroodjesService().getPrijsVanSoort(bestellijn.getBroodje());
            String[] beleg = bestellijn.getBeleg().split(" ");
            if(beleg.length > 1) {
                for (String s : beleg) {
                    price += Service.getInstance().getBelegService().getPrijsVanSoort(s);
                }
            }
            if(lowestPrice > price - lastPrice){
                lowestPrice = price - lastPrice;
            }
        }
        return Math.round((price - lowestPrice) * 100.0) / 100.0;
    }
}
