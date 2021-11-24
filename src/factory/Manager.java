package factory;

import cupboard.Cupboard;
import fridge.Fridge;

public class Manager {

    private final String name;
    private  static Manager instance;

    private Manager(String name){
        this.name = name;
    }

    public static  Manager getInstance(String context){
        if(instance == null){
            instance = new Manager(context);
        }
        return instance;
    }

    public String getName(){
        return name;
    }

    public Factory createFactory (String typeOfFactory){
        switch (typeOfFactory){
            case "Fridge": return  new Fridge();
            case "Cupboard": return new Cupboard();
            default: return null;
        }
    }
}
