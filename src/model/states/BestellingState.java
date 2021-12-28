package model.states;

import model.Bestelling;
import model.DomainException;

public abstract class BestellingState implements BestellingStateInterface{

    protected Bestelling bestelling;

    public BestellingState(Bestelling bestelling){
        this.bestelling = bestelling;
    }

    public Bestelling getBestelling() {
        return bestelling;
    }

    public void startBestelling() throws DomainException{
        throw new DomainException("can't use 'startBestelling'");
    }

    public void toevoegenBroodje() throws DomainException{
        throw new DomainException("can't use 'toevoegenBroodje'");

    }

    public void verwijderBroodje() throws DomainException{
        throw new DomainException("can't use 'verwijderBroodje'");

    }
    public void toevoegenIdentiekBroodje() throws DomainException{
        throw new DomainException("can't use 'toevoegenIdentiekBroodje'");
    }

    public void toevoegenBeleg() throws DomainException{
        throw new DomainException("can't use 'toevoegenBeleg'");

    }

    public void afsluiten() throws DomainException{
        throw new DomainException("can't use 'afsluiten'");
    }

    public void annuleren() throws DomainException{
        throw new DomainException("can't use 'annuleren'");
    }

    public void betalen() throws DomainException{
        throw new DomainException("can't use 'betalen'");

    }

    public void zendNaarKeuken() throws DomainException{
        throw new DomainException("can't use 'zendNaarKeuken'");
    }

    public void startBereiding() throws DomainException{
        throw new DomainException("can't use 'startBereiding'");
    }

    public void afgewerkt() throws DomainException{
        throw new DomainException("can't use 'afgewerkt'");

    }

}
