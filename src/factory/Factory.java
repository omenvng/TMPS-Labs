package factory;

import product.Product;
import product.ProductType;

import java.util.ArrayList;
import java.util.Date;

public interface Factory {
    int getId();

    private Product create(ProductType type) {
        return null;
    }

    void testCreate(ProductType type);

    Date randomDate();

    String randomName();

    float randomPrice();

    ArrayList<Product> getProducts();

    default void showProducts(){
        for (Product product : getProducts())
            product.showProduct();
    }

    static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    String[] names = new String[] {
            "Foodflow", "VivaMeal", "Lunchette", "Nutritella", "MealAndMeal", "Shprite", "Nutrice", "Eation",
            "Gourmet", "KitchenFuel", "Suppet", "Foodiest", "Coca-cova", "SquadFood", "Luncher", "Maltad",
            "Flavorful", "Kasseroles", "Stacks", "Foodiogo", "Foodi", "Stewd", "Ediblez", "Krispy" };
}
