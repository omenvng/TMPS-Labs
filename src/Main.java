import factory.Factory;
import factory.Manager;
import product.ProductType;
import tool.CashMachine;
import tool.Computer;
import tool.Scanner;
import workers.Accountant;
import workers.Cashier;
import workers.Merchandiser;

public class Main {

    public static void main(String[] args) {
        Manager manager = Manager.getInstance("Oleg");
        System.out.println(manager.getName());
        Factory fridge = manager.createFactory("Fridge");
        Factory cupboard = manager.createFactory("Cupboard");

        fridge.testCreate(ProductType.MilkProduct);
        fridge.testCreate(ProductType.Seafood);
        fridge.testCreate(ProductType.Drinks);
        fridge.testCreate(ProductType.Seafood);
        fridge.testCreate(ProductType.Drinks);

        cupboard.testCreate(ProductType.BakeryProducts);
        cupboard.testCreate(ProductType.Porridge);
        cupboard.testCreate(ProductType.Sweets);
        cupboard.testCreate(ProductType.BakeryProducts);
        cupboard.testCreate(ProductType.Porridge);

        fridge.showProducts();
        cupboard.showProducts();

        Accountant accountant = new Accountant("Sean", new Computer());

        accountant.action();

        Cashier cashier = new Cashier("Maria", new CashMachine());

        cashier.action();

        Merchandiser merchandiser = new Merchandiser("Juan", new Scanner());

        merchandiser.action();

    }
}
