package model;

import java.util.ArrayList;

public interface Observer {
    public void update(Bestelling bestelling, BestellingEvents bestellingEvents);
}
