package model.kortingen;

public enum KortingEnum {

    GeenKorting("Geen korting", "GeenKorting"),
    TienProcentKorting("10% korting", "TienProcentKorting"),
    GoedkoopsteGratisKorting("Goedkoopste broodje gratis", "GoedkoopsteGratisKorting");


    public final String NAME;
    public final String PATH;

    private KortingEnum(String name, String PATH) {
        this.NAME = name;
        this.PATH = PATH;
    }


}
