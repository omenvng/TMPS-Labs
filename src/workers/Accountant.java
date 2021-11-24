package workers;

import tool.Computer;

public class Accountant extends Worker{

    public Accountant() {
    }

    public Accountant(String name) {
        super(name);
    }

    public Accountant(String name, Computer tool) {
        super(name, tool);
    }
}
