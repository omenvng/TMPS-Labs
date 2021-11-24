package product;

import java.util.Date;

public interface Product {
    int getId();
    String getName();
    Date getExpirationDate();
    float getPrice();
    int getIdOfSpace();

    default void showProduct() {
        System.out.println("Product:"+getClass().getSimpleName());
        System.out.println("Id:\t\t"+getId());
        System.out.println("Name:\t"+getName());
        System.out.println("Price:\t"+getPrice());
        System.out.println("Date:\t"+getExpirationDate().getDate()+"."+(getExpirationDate().getMonth()+1)+"."+(getExpirationDate().getYear()+1900)+"\n");
    }
}
