package model;

public abstract class Product {
    private String title;
    private String id;
    private boolean available = true;

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public Product(String title, String id) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("De title kan niet leeg zijn");
        }
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("id kan niet leeg zijn");
        }
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public abstract double berekenPrijs(int days);


}
