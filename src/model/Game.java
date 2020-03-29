package model;

public class Game extends Product {
    private static final int PRIJS = 3;

    public Game(String title, String id) {
        super(title, id);
    }

    @Override
    public double berekenPrijs(int days) {
        return PRIJS * days;
    }
}
