package model.states;

import model.Bestelling;

public class InWachtrij extends BestellingState{
    public InWachtrij(Bestelling bestelling) {
        super(bestelling);
    }

    @Override
    public void startBereiding() {
        bestelling.setState(new InBereiding(bestelling));
    }
}
