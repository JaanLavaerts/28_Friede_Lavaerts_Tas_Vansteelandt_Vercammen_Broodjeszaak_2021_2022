package model.states;

import model.Bestelling;
import model.Broodje;
import model.DomainException;

public class InWacht extends BestellingState {

    public InWacht(Bestelling bestelling) {
        super(bestelling);
    }

    @Override
    public void startBestelling(){
        bestelling.setState(new InBestelling(bestelling));
    }

}
