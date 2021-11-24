package workers;

import tool.Scanner;

public class Merchandiser extends Worker{

    public Merchandiser() {
    }

    public Merchandiser(String name) {
        super(name);
    }

    public Merchandiser(String name, Scanner tool) {
        super(name, tool);
    }
}