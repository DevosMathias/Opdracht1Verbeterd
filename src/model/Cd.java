package model;

public class Cd extends Product {
    private static final double PRIJS = 1.5;

    public Cd(String title, String id) {
        super(title, id);
    }

    @Override
    public double berekenPrijs(int days) {
        return PRIJS * days;
    }
}
