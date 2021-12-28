package model.states;

import model.Bestelling;

public class Betaald extends BestellingState {


    public Betaald(Bestelling bestelling) {
        super(bestelling);
    }

    @Override
    public void zendNaarKeuken() {
        bestelling.setState(new InWachtrij(bestelling));
    }
}
