package model;

import model.states.BestellingState;
import model.states.BestellingStateInterface;
import model.states.InWacht;

import java.util.ArrayList;
import java.util.HashMap;

public class Bestelling implements BestellingStateInterface {

    public static int getVOLGNR() {
        return VOLGNR;
    }

    private static int VOLGNR = 0;

    private ArrayList<Bestellijn> bestelLijnen;

    private BestellingState state;
    private int volgnr;

    public Bestelling() {
        this.bestelLijnen = new ArrayList<>();
        this.state = new InWacht(this);
        volgnr = VOLGNR;
        VOLGNR += 1;
    }

    public void getBestelling() {

    }

    public int getVolgnr(){
        return volgnr;
    }

    public void setState(BestellingState state){
        this.state = state;
    }

    public void addBroodje(Broodje broodje) {
        Bestellijn bestellijn = new Bestellijn(broodje);
        bestelLijnen.add(bestellijn);
    }

    public void removeBroodje(Bestellijn bestellijn) {
        bestelLijnen.remove(bestellijn);
    }

    public ArrayList<Bestellijn> getLijstBestellijnen() {
        return bestelLijnen;
    }

    @Override
    public void startBestelling(){
        try {
            state.startBestelling();
        } catch (DomainException e) {
            throw new DomainException(e.getMessage());
        }
    }

    @Override
    public void toevoegenBroodje(){
        try {
            state.toevoegenBroodje();
        } catch (DomainException e) {
            throw new DomainException(e.getMessage());
        }
    }

    @Override
    public void verwijderBroodje() {
        try {
            state.verwijderBroodje();
        } catch (DomainException e) {
            throw new DomainException(e.getMessage());
        }
    }

    @Override
    public void toevoegenIdentiekBroodje() {
        try {
            state.toevoegenIdentiekBroodje();
        } catch (DomainException e) {
            throw new DomainException(e.getMessage());
        }
    }

    @Override
    public void toevoegenBeleg()  {
        try {
            state.toevoegenBeleg();
        }catch (DomainException e){
            throw new DomainException(e.getMessage());
        }
    }

    @Override
    public void afsluiten()  {
        try {
            state.afsluiten();
        } catch (DomainException e) {
            throw new DomainException(e.getMessage());
        }
    }

    @Override
    public void annuleren()  {
        try {
            state.annuleren();
            VOLGNR -= 1;
        } catch (DomainException e) {
            throw new DomainException(e.getMessage());
        }
    }

    @Override
    public void betalen()  {
        try {
            state.betalen();
        } catch (DomainException e) {
            throw new DomainException(e.getMessage());
        }
    }

    @Override
    public void zendNaarKeuken() {
        try {
            state.zendNaarKeuken();
        } catch (DomainException e) {
            throw new DomainException(e.getMessage());
        }
    }

    @Override
    public void startBereiding() {
        try {
            state.startBereiding();
        } catch (DomainException e){
            throw new DomainException(e.getMessage());
        }
    }

    @Override
    public void afgewerkt() {
        try {
            state.afgewerkt();
        } catch (DomainException e) {
            throw new DomainException(e.getMessage());
        }
    }

    public void addBestellijn(Bestellijn bestellijn) {
        bestelLijnen.add(bestellijn);
    }
}