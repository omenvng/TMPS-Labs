package fridge;

import factory.Factory;
import product.Product;
import product.ProductType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Fridge implements Factory {
    private ArrayList<Product> products = new ArrayList<>();
    private final int id = i++;
    private static int i = 0;
    private int capacity = 5;

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    @Override
    public Date randomDate() {

        GregorianCalendar gc = new GregorianCalendar();

        int year = Factory.randBetween(2021, 2022);

        gc.set(Calendar.YEAR, year);

        int dayOfYear = Factory.randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));

        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        return new Date(gc.getTimeInMillis());
    }

    @Override
    public float randomPrice() {
        double price =Math.random()*50+0.5;
        price = (int)(Math.round(price * 100))/100.0;
        return (float) price;
    }

    @Override
    public String randomName() {
        return names[(int) (Math.random()* (names.length-1))];
    }

    private Product create(ProductType type) {
        switch (type) {
            case MilkProduct: {
                Product milkProduct = new MilkProduct.Builder(randomName(),randomDate(),randomPrice()).setIdOfSpace(id).build();
                products.add(milkProduct);
                capacity--;
                return milkProduct;
            }
            case Seafood: {
                Product seafood = new Seafood.Builder(randomName(),randomDate(),randomPrice()).setIdOfSpace(id).build();
                products.add(seafood);
                capacity--;
                return seafood;
            }
            case Drinks: {
                Product drink = new Drinks.Builder(randomName(),randomDate(),randomPrice()).setIdOfSpace(id).build();
                products.add(drink);
                capacity--;
                return drink;
            }
            default:
                return null;
        }
    }

    public void testCreate(ProductType type) {
        if (capacity == 0) {
            System.out.println("The fridge is full of products. We need to create new fridge!\n");
        } else
            create(type);
    }
}
