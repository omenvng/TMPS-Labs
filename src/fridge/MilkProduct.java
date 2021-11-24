package fridge;

import product.Product;

import java.util.Date;

public class MilkProduct implements Product {
    private final int  id = i++;
    private final String name;
    private final Date expirationDate;
    private final float price;
    private int idOfSpace;
    private static int i = 0;

    public MilkProduct(Builder builder) {
        this.name = builder.name;
        this.expirationDate = builder.expirationDate;
        this.price = builder.price;
        this.idOfSpace = builder.idOfSpace;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public float getPrice() {
        return price;
    }

    public int getIdOfSpace() {
        return idOfSpace;
    }

    public void getProduct(){
        if (idOfSpace == -1)
            System.out.println("Milk Product ID: " + id + "\nName: " + name + "\nExpiration Date: " + expirationDate + "\nPrice: " + price );
        else
            System.out.println("Milk Product ID: " + id + "\nName: " + name + "\nExpiration Date: " + expirationDate + "\nPrice: " + price + "\nSpace ID: " + idOfSpace );
    }

    static class Builder{
        private final String name;
        private final Date expirationDate;
        private final float price;
        private int idOfSpace = -1;

        public Builder(String name, Date expirationDate, float price) {
            this.name = name;
            this.expirationDate = expirationDate;
            this.price = price;
        }

        public Builder setIdOfSpace(int idOfSpace){
            this.idOfSpace = idOfSpace;
            return this;
        }

        public MilkProduct build(){
            return new MilkProduct(this);
        }
    }
}

