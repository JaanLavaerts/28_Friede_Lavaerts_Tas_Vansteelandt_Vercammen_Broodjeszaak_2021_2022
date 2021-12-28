package model.states;

import model.Bestelling;

public class Afgesloten extends BestellingState{

    public Afgesloten(Bestelling bestelling) {
        super(bestelling);
    }

    @Override
    public void betalen(){
        bestelling.setState(new Betaald(bestelling));
    }

    @Override
    public void annuleren() {
        bestelling = null;
    }
}
