package model;

public interface Subject {
    public void addObserver(Observer observer, BestellingEvents bestellingEvents);
    public void removeObserver(Observer observer, BestellingEvents bestellingEvents);
    public void notifyObserver(BestellingEvents bestellingEvents);
}
