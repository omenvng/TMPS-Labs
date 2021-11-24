package workers;

import tool.Tool;

public abstract class Worker {

    private Tool tool;

    private String name = "Bob";

    public Worker() {
    }
    public Worker(String name) {
        this.name = name;
    }

    public Worker(String name, Tool tool) {
        this.name = name;
        this.tool = tool;
    }

    public void action() {
        System.out.print("From " + name + ": ");
        this.tool.action();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
