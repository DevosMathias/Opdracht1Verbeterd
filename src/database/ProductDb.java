package database;

import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductDb {
    //private Shop shop;
    private ArrayList<Product> producten;

    public ProductDb() {
        producten = new ArrayList<>();
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Een product kan niet leeg zijn");
        }
        this.producten.add(product);
    }

    public void schrijfWeg(File file) {
        try (PrintWriter printer = new PrintWriter(file)) {
            for(Product product: this.producten) {
                if (product != null) {
                    String type = "";
                    if (product instanceof Movie) {
                        type = "M";
                    } else if (product instanceof Game) {
                        type = "G";
                    } else if (product instanceof Cd) {
                        type = "C";
                    }
                    printer.println(product.getTitle() + ";" + product.getId() + ";" + type);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Fout bij vinden van het bestand");
        }
    }

    public void leesBestandIn(File file) {
        try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner linescanner = new Scanner(line);
                linescanner.useDelimiter(";");
                String title = linescanner.next();
                String id = linescanner.next();
                String type = linescanner.next();

                Product product = null;

                if (type.equals("M")) {
                    product = new Movie(title, id);
                } else if (type.equals("G")) {
                    product = new Game(title, id);
                } else if (type.equals("C")) {
                    product = new Cd(title, id);
                }
                if (product == null) {
                    throw new IllegalArgumentException("Een product kan niet leeg zijn");
                }
                this.producten.add(product);
            }


        } catch (FileNotFoundException e) {
            System.out.println("Fout bij vinden van het bestand");
        }
    }

    public ArrayList<Product> getProducten() {
        return producten;
    }
}
