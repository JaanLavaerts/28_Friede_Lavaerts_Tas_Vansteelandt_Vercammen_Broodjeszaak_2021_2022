package model;

public class Beleg {

    private String beschrijving;
    private double prijs;
    private int instock;
    private int verkocht;

    public Beleg(String beschrijving, double prijs, int instock, int verkocht) {
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        this.instock = instock;
        this.verkocht = verkocht;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public int getInstock() {
        return instock;
    }

    public void setInstock(int instock) {
        this.instock = instock;
    }

    public int getVerkocht() {
        return verkocht;
    }

    public void setVerkocht(int verkocht) {
        this.verkocht = verkocht;
    }

}
