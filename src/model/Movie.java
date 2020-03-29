package model;

public class Movie extends Product {
    private static final int PRIJS = 5;

    public Movie(String title, String id) {
        super(title, id);
    }

    @Override
    public double berekenPrijs(int days) {
        double price = PRIJS;
        int daysLeft = days - 3;
        if (daysLeft > 0) {
            price += (daysLeft * 2);
        }
        return price;
    }
}
