package model.states;

import model.Bestelling;
import model.DomainException;

public class InBestelling extends BestellingState {
    public InBestelling(Bestelling bestelling) {
        super(bestelling);
    }

    @Override
    public void toevoegenBroodje() {

        bestelling.setState(new InBestelling(bestelling));
    }

    @Override
    public void verwijderBroodje() {
        bestelling.setState(new InBestelling(bestelling));

    }

    @Override
    public void toevoegenIdentiekBroodje() {
        bestelling.setState(new InBestelling(bestelling));

    }

    @Override
    public void toevoegenBeleg() {
        bestelling.setState(new InBestelling(bestelling));

    }

    @Override
    public void afsluiten() {
        bestelling.setState(new Afgesloten(bestelling));
    }

    @Override
    public void annuleren() throws DomainException {
        bestelling = null;
    }
}
