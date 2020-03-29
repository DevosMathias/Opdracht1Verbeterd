package view;

import database.ProductDb;
import model.*;

import javax.swing.*;

public class ShopUi {
    private Shop shop;
    private ProductDb database;
    private int id = 0;

    public ShopUi() {
        shop = new Shop();
        database = new ProductDb();
    }

    public void showMenu() {
        String menu = "1. Add product\n2. Show product\n3. Show rental price\n4. Show products\n5. Rent product\n6. Return product\n\n0. Quit";
        int choice = -1;
        while (choice != 0) {
            String choiceString = JOptionPane.showInputDialog(menu);
            choice = Integer.parseInt(choiceString);
            if (choice == 1) {
                addProduct();
                showMenu();
            } else if (choice == 2) {
                showProduct();
            } else if (choice == 3){
                showPrice();
            } else if (choice == 4) {
                showProducts();
            } else if (choice == 5) {
                rentProduct();
            } else if (choice == 6) {
                returnProduct();
            }
        }
        shop.close();
    }

    private void addProduct(){
        String title = JOptionPane.showInputDialog("Enter the title:");
        //String id = JOptionPane.showInputDialog("Enter the id:");
        String type = JOptionPane.showInputDialog("Enter the type (M for movie/G for game/C for cd):");
        Product product = null;
        id++;
        if (type.equals("M")) {
            product = new Movie(title, Integer.toString(id));
        } else if (type.equals("G")) {
            product = new Game(title, Integer.toString(id));
        } else if (type.equals("C")) {
            product = new Cd(title, Integer.toString(id));
        }

        shop.addProduct(product);

    }

    private void showProduct() {
        String id = JOptionPane.showInputDialog("Enter the id:");
        int idx = -1;
        boolean found = false;
        for(int i = 0; i < shop.getProducts().size() && !found; i++)
        {
            if(shop.getProducts().get(i).getId().equals(id))
            {
                idx = i;
                found = true;
            }
        }
        if(found)
        {
            JOptionPane.showMessageDialog(null, shop.getProduct(idx).getTitle() + " " + (shop.getProduct(idx).isAvailable() ? "Is available" : "Is not available"));
        }
    }

    private void showProducts() {
        String product = "";

        for (int i = 0; i < shop.getProducts().size(); i++) {
            if (shop.getProducts().get(i) != null && shop.getProducts().get(i) instanceof Movie) {
                product += "Movie " + shop.getProducts().get(i).getId() + " " + shop.getProducts().get(i).getTitle() + "\n";
            }
        }

        for (int i = 0; i < shop.getProducts().size(); i++) {
            if (shop.getProducts().get(i) != null && shop.getProducts().get(i) instanceof Game) {
                product += "Game " + shop.getProducts().get(i).getId() + " " + shop.getProducts().get(i).getTitle() + "\n";
            }
        }

        for (int i = 0; i < shop.getProducts().size(); i++) {
            if (shop.getProducts().get(i) != null && shop.getProducts().get(i) instanceof Cd) {
                product += "Cd " + shop.getProducts().get(i).getId() + " " + shop.getProducts().get(i).getTitle() + "\n";
            }
        }
        JOptionPane.showMessageDialog(null, product);
    }

    private void showPrice() {
        String id = JOptionPane.showInputDialog("Enter the id:");
        int idx = -1;
        boolean found = false;
        for(int i = 0; i < shop.getProducts().size() && !found; i++){
            if(shop.getProducts().get(i).getId().equals(id)){
                idx = i;
                found = true;
            }
        }
        if(found){
            String daysString = JOptionPane.showInputDialog("Enter the number of days:");
            int days = Integer.parseInt(daysString);
            JOptionPane.showMessageDialog(null, shop.getPrice(idx ,days));
        }
    }

    private void rentProduct() {
        String id = JOptionPane.showInputDialog("Enter the id:");
        int idx = -1;
        boolean found = false;
        for(int i = 0; i < shop.getProducts().size() && !found; i++){
            if(shop.getProducts().get(i).getId().equals(id)){
                idx = i;
                found = true;
            }
        }
        if(found){
            shop.rentProduct(shop.getProducts().get(idx));
            shop.getProducts().get(idx).setAvailable(false);
            JOptionPane.showMessageDialog(null, "Product is rented succesfully!");
        }
    }

    private void returnProduct() {
        String id = JOptionPane.showInputDialog("Enter the id:");
        int idx = -1;
        boolean found = false;
        for(int i = 0; i < shop.getProducts().size() && !found; i++){
            if(shop.getProducts().get(i).getId().equals(id)){
                idx = i;
                found = true;
            }
        }
        if(found){
            shop.rentProduct(shop.getProducts().get(idx));
            shop.getProducts().get(idx).setAvailable(true);
            JOptionPane.showMessageDialog(null, "Product is rented succesfully!");
        }
    }
}
