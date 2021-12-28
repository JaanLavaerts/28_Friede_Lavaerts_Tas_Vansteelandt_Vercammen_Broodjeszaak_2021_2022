package model.kortingen;

import model.Bestelling;

public interface Korting {

    double getPrice(Bestelling bestelling);
}
