package model.kortingen;

import model.DomainException;

import java.lang.reflect.InvocationTargetException;

public class KortingFactory {

    public static Korting getKorting(String name){

        Korting korting = null;
        try {
            Class kortingClass = Class.forName("model.kortingen." + name);
            Object kortingObject = null;
            kortingObject = kortingClass.getConstructor().newInstance();
            korting = (Korting) kortingObject;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new DomainException("Class not found");
        }
        return korting;
    }

}
