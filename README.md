# Laboratory work №1:  _Software Design Techniques and Mechanisms_
# Topic: _Creational Design Patterns_
# Author: _Vangheli Dumitru, FAF-193_

---
## Table of contents:
* [Introduction](#introduction)
* [Implementation](#implementation)
* [Results](#results)
---

## _Introduction_

In this laboratory work were been implemented `Creational Design Patterns`. What is the purpose of such patterns?
>These patterns are responsible for the convenient and safe creation of new objects or even entire families of objects

* ### 1) Builder

>The Builder Pattern separates the construction of a complex object from its representation so that different representations can result from the same design process.
![Builder](https://upload.wikimedia.org/wikipedia/ru/2/28/Builder.gif)

* ### 2) Abstract factory

>An abstract factory is a generative design pattern that allows you to create families of related objects without being tied to the specific classes of objects that you create.
![Abs_Factory](https://refactoring.guru/images/patterns/diagrams/abstract-factory/solution2.png)

* ### 3) Factory

>A factory method is an ancestral design pattern that defines a common interface for creating objects in a superclass, allowing subclasses to change the type of objects they create.
![Factory](https://refactoring.guru/images/patterns/diagrams/factory-method/solution1.png)

* ### 4) Singleton

>A singleton is a generative design pattern that ensures that a class has only one instance and provides a global access point to it.
![Singleton](https://user-images.githubusercontent.com/6358475/135911578-1f659174-d619-415f-b2cd-3d11e29d70ea.png)

---

## _Implementation_

There was implemented the simulation of the `Store` to show how design patterns work. The main idea was that there will be the `Manager`, the only one, and he can manage some money and buy another equipment like `Fridge` or `Cupboard` (or Shelf, if you want) to store `Products`.

### `Builder`:
There is common Interface named `Product` and different classes implement it: `Drinks`, `Seafood`, `Sweets`, etc.
The mandatory attributes to be in instance of `Product` are Name, Expiration Date and Price, while id of equipment where product is placed is optional.
Also, all mandatory attributes are randomly generated in `Factory`, which are discussed further.

The example of `Builder` in BakeryProducts class:

```java
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

        public BakeryProducts build(){
            return new BakeryProducts(this);
        }
    }
```

### `Factory`:
There is Interface named `Factory` and it is implemented in `Fridge` and `Cupboard` classes, which are Factories and they create new objects:
`Fridge` can create `Drinks`, `Seafood` and `Milk Products`,
`Cupboard` cam create `Sweets`, `Porridge` and `Bakery Products`

For example, main part of `Cupboard` class is:
```java
    private Product create(String type){
        switch (type){
            case "Sweets":{
                Product sweets = new Sweets.Builder(randomName(),randomDate(),randomPrice()).setIdOfSpace(id).build();
                products.add(sweets);
                capacity--;
                return  sweets;
            }
            case "Porridge":{
                Product porridge = new Porridge.Builder(randomName(),randomDate(),randomPrice()).setIdOfSpace(id).build();
                products.add(porridge);
                capacity--;
                return  porridge;
            }
            case "BakeryProducts":
            {
                Product bakeryProducts = new BakeryProducts.Builder(randomName(),randomDate(),randomPrice()).setIdOfSpace(id).build();
                products.add(bakeryProducts);
                capacity--;
                return  bakeryProducts;
            }
            default: return null;
        }
    }
```

### `Singleton`:

The `Manager` was implemented as `Singleton`, this object can be created only one time:
```java
    private String name;
    private static Manager instance;

    private Manager(String name){
        this.name = name;
    }

    public static  Manager getInstance(String context){
        if(instance == null){
            instance = new Manager(context);
        }
        return instance;
    }
```

### `Abstract factory`:

Also, the `Manager` is Abstract factory, it can create (or buy, if you want) more equipment like `Fridge` or `Cupboard`

The example code of `Manager`:

```java
    public Factory createFactory (String typeOfFactory){
        switch (typeOfFactory){
            case "Fridge": return  new Fridge();
            case "Cupboard": return new Cupboard();
            default: return null;
        }
```

## _Results_

- ### Cupboard factory partial code
![image](https://user-images.githubusercontent.com/6358475/135914833-ebeca66e-f241-4e0f-98a6-4d04f0b4368e.png)

- ### Abstract factory full code
![image](https://user-images.githubusercontent.com/6358475/135914956-bb6ccaec-5e69-411d-b68d-2a3c94ae1f10.png)

- ### Seafood and builder partial code 
![image](https://user-images.githubusercontent.com/6358475/135915112-6f4c9e5a-38cd-441d-9887-38dd35a05871.png)

- ### Factory interface code
![image](https://user-images.githubusercontent.com/6358475/135915228-d3fa93e9-096f-4a4a-9318-eee80151ace0.png)

- ### The output
![image](https://user-images.githubusercontent.com/6358475/135915723-042cf3a9-bce6-4e47-9ad3-07b881c0ac30.png)


## Conclusions 
After completing this implementation I have reached to the conclusion that Creational Desingn Patterns provide object creation mechanisms that can create objects in a controlled manner that are suitable to the situation.In this way, they help reduce complexities and instability, while also offering increased flexibility and allowing for the reuse of code.