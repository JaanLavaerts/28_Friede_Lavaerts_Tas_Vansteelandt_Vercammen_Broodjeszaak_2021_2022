package model.states;

import model.Broodje;
import model.DomainException;

public interface BestellingStateInterface {

    public void startBestelling() throws DomainException;

    public void toevoegenBroodje() throws DomainException;

    public void verwijderBroodje() throws DomainException;

    public void toevoegenIdentiekBroodje() throws DomainException;

    public void toevoegenBeleg() throws DomainException;

    public void afsluiten() throws DomainException;

    public void annuleren()throws DomainException;

    public void betalen()throws DomainException;

    public void zendNaarKeuken()throws DomainException;

    public void startBereiding() throws DomainException;

    public void afgewerkt() throws DomainException;
}
