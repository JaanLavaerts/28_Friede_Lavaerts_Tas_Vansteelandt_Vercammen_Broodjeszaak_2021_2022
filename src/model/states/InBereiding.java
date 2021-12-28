package model.states;

import model.Bestelling;

public class InBereiding extends BestellingState{

    public InBereiding(Bestelling bestelling) {
        super(bestelling);
    }

    @Override
    public void afgewerkt() {
        bestelling = null;
    }

}
