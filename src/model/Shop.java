package model;

import database.ProductDb;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Shop {
    private ArrayList<Product> producten;
    private ProductDb database;

    public Shop() {
        producten = new ArrayList<>();
        database = new ProductDb();
        File file = new File("C:\\Users\\Eigenaar\\Documents\\Toegepaste informatica\\2019-2020\\Semester 2\\OOO\\Week 1\\producten.txt");
        this.database.leesBestandIn(file);
        for (int i = 0; i < this.database.getProducten().size(); i++) {
            this.producten.add(this.database.getProducten().get(i));
        }
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product kan niet leeg zijn");
        }
        this.producten.add(product);
        this.database.addProduct(product);
    }

    public Product getProduct(int idx) {
        return producten.get(idx);
    }

    public List<Product> getProducts() {
        return producten;
    }

    public double getPrice(int idx, int days) {
        if (idx > this.producten.size()-1 || idx < 0) {
            throw new IllegalArgumentException("Deze id bestaat niet");
        }

        return producten.get(idx).berekenPrijs(days);
    }

    public void close() {
        File file = new File("C:\\Users\\Eigenaar\\Documents\\Toegepaste informatica\\2019-2020\\Semester 2\\OOO\\Week 1\\producten.txt");
        database.schrijfWeg(file);
    }

    public void rentProduct(Product product) {
        if (this.producten.contains(product)) {
            product.setAvailable(false);
        }
    }

    public void returnProduct(Product product) {
        if (this.producten.contains(product)) {
            product.setAvailable(true);
        }
    }


}
