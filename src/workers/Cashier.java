package workers;

import tool.CashMachine;

public class Cashier extends Worker {

    public Cashier() {
    }

    public Cashier(String name) {
        super(name);
    }

    public Cashier(String name, CashMachine tool) {
        super(name, tool);
    }
}