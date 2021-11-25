# Laboratory work No. 2
_ FAF-193, Vangheli Dumitru_

## Task 💠
Целью данной лабораторной работы было имплементировать 5 структурных паттернов.

## The structural patterns used📓
1. _Adapter_
2. _Bridge_
3. _Composite_
4. _Decorator_
5. _Filter_

## _Adapter_
An adapter is a structural design pattern that allows objects with incompatible interfaces to work together.

![alt text](https://refactoring.guru/images/patterns/content/adapter/adapter.png "Logo Title Text 1")

## _Bridge_
A bridge is a structural design pattern that divides one or more classes into two separate hierarchies -abstraction and implementation, allowing them to be changed independently of each other.
![alt text](https://refactoring.guru/images/patterns/content/bridge/bridge.png "Logo Title Text 1")

## _Composite_

A composite pattern is a structural design pattern that combines objects into a tree structure to represent a hierarchy from the particular to the whole. The linker allows clients to access individual objects and groups of objects in the same way. The pattern defines a hierarchy of classes that can simultaneously consist of primitive and complex objects, simplifies the architecture of the client, makes the process of adding new types of object simpler.

![alt text](https://refactoring.guru/images/patterns/content/composite/composite.png "Logo Title Text 1")

## _Decorator_
![Decorator](https://refactoring.guru/images/patterns/cards/decorator-mini-2x.png)

A decorator is a structural design pattern that allows you to dynamically add new functionality to objects by wrapping them in useful "wrappers".
The decorator has an alternative name - wrapper. It more accurately describes the essence of the pattern: you put the target object in another wrapper object, which triggers the basic behavior of the object, and then adds something of its own to the result.

Both objects have a common interface, so there is no difference for the user which object to work with — clean or wrapped. You can use several different wrappers at the same time — the result will have the combined behavior of all wrappers at once.

With the help of the decorator, we wrap a function into another with additional functionality 

## _Filter_

Filter or Criteria pattern - allows developers to filter a set of objects using different criteria and separating them using logical operations. This type of design pattern falls under the structural pattern, since this pattern combines several criteria to obtain uniform criteria.
# _Lab2_TMPS_Adapter_

The Builder interface is our most common interface and provides a method that accepts the type of building and its location:

```java
public interface Builder {  
    public void build(String type, String location);
}
```

The Advanced Builder interface provides two methods: one for building a house and the other for building a skyscraper::

```java
public interface AdvancedBuilder {  
    public void buildHouse(String location);
    public void buildSkyscrapper(String location);
}
```

The two interfaces are unrelated. Yes, they share a theme, but they are not related to the code.

At this stage, a specific class is created that implements the Advanced Builder interface :

```java
public class HouseBuilder implements AdvancedBuilder {  
    @Override
    public void buildHouse(String location) {
        System.out.println("Building a house located in the " + location + "area!");
    }

    @Override
    public void buildSkyscrapper(String location) {
        //don't implement
    }
}
```

And, of course, by the same analogy, another specific class is created:

```java
public class SkyscrapperBuilder implements AdvancedBuilder {  
    @Override
    public void buildSkyscrapper(String location) {
        System.out.println("Building a skyscrapper in the " + location + "area!");
    }

    @Override
    public void buildHouse(String location) {
        //don't implement
    }
}
```

Here is part of the adapter - to connect these two interfaces, a Builder Adapter is created that implements Builder :

```java
public class BuilderAdapter implements Builder {  
    AdvancedBuilder advancedBuilder;

    public BuilderAdapter(String type) {
        if(type.equalsIgnoreCase("House")) {
            advancedBuilder = new HouseBuilder();
        } else if(type.equalsIgnoreCase("Skyscrapper")) {
            advancedBuilder = new SkyscrapperBuilder();
        }
    }

    @Override
    public void build(String type, String location) {
        if(type.equalsIgnoreCase("House")) {
            advancedBuilder.buildHouse(location);
        } else if(type.equalsIgnoreCase("Skyscrapper")) {
            advancedBuilder.buildSkyscrapper(location);
        }
    }
}
```

Working with the adapter, we can finally implement a solution and use the Builder interface method with the Builder Adapter to build supported building types.

```java
public class BuilderImplementation implements Builder {  
    BuilderAdapter builderAdapter;

    @Override
    public void build(String type, String location) {
        if(type.equalsIgnoreCase("House") || type.equalsIgnoreCase("Skyscrapper")) {
            builderAdapter = new BuilderAdapter(type);
            builderAdapter.build(type, location);
        } else {
            System.out.println("Invalid building type.");
        }
    }
}
```

And watch the result:

```java
public class Main {  
    public static void main(String[] args) {
        BuilderImplementation builderImpl = new BuilderImplementation();

        builderImpl.build("house", "Downtown");
        builderImpl.build("Skyscrapper", "City Center");
        builderImpl.build("Skyscrapper", "Outskirts");
        builderImpl.build("Hotel", "City Center");
    }
}
```

Executing the above code will give:

```java
Building a house located in the Downtown area!  
Building a skyscrapper in the City Center area!  
Building a skyscrapper in the Outskirts area!  
Invalid building type.  
```

# _Lab2_TMPS_Bridge_

As usual, the interface is the starting point:

```java
public interface FeedingAPI {  
    public void feed(int timesADay, int amount, String typeOfFood);
}
```

Then two specific classes implement this:

```java
public class BigDog implements FeedingAPI {  
    @Override
    public void feed(int timesADay, int amount, String typeOfFood) {
        System.out.println("Feeding a big dog, " + timesADay + " times a day with " + 
            amount + " g of " + typeOfFood);
    }
}

public class SmallDog implements FeedingAPI {  
    @Override
    public void feed(int timesADay, int amount, String typeOfFood) {
        System.out.println("Feeding a small dog, " + timesADay + " times a day with " + 
            amount + " g of " + typeOfFood);
    }
}
```

Using the Feeding API , an abstract Animal class is created :

```java
public abstract class Animal {  
    protected FeedingAPI feedingAPI;

    protected Animal(FeedingAPI feedingAPI) {
        this.feedingAPI = feedingAPI;
    }
    public abstract void feed();
}
```

This is where the Bridge pattern comes into effect. A bridge class is created that separates the abstract Animal class from its implementation:

```java
public class Dog extends Animal{  
    private int timesADay, amount;
    private String typeOfFood;

    public Dog(int timesADay, int amount, String typeOfFood, FeedingAPI feedingAPI) {
        super(feedingAPI);
        this.timesADay = timesADay;
        this.amount = amount;
        this.typeOfFood = typeOfFood;
    }

    public void feed() {
        feedingAPI.feed(timesADay, amount, typeOfFood);
    }
}
```

And we are watching the result:

```java
public class Main {  
    public static void main(String[] args) {
        Animal bigDog = new Dog(3, 500, "Meat", new BigDog());
        Animal smallDog = new Dog(2, 250, "Granules", new SmallDog());

        bigDog.feed();
        smallDog.feed();
    }
}
```

Running this code will give:

```java
Feeding a big dog, 3 times a day with 500 g of Meat  
Feeding a small dog, 2 times a day with 250 g of Granules  
```

# _Lab2_TMPS_Composite_

Let 's start with the Employee class . This class will be created several times to form a group of employees:

```java
public class Employee {  
    private String name;
    private String position;
    private int wage;
    private List<Employee> coworkers;

    public Employee(String name, String position, int wage) {
        this.name = name;   
        this.position = position;
        this.wage = wage;
        coworkers = new ArrayList<Employee>();
    }

    public void addCoworker(Employee employee) {
        coworkers.add(employee);
    }

    public void removeCoworker(Employee employee) {
        coworkers.remove(employee);
    }

    public List<Employee> getCoworkers() {
        return coworkers;
    }

    public String toString() {
        return "Employee : | Name: " + name + ", Position: " + position + ", Wage: "
             + wage + " |";
    }
}
```

There is an Employee list in the class, this is our group of objects that we want to define as a separate object.

```java
public class StackAbuseJavaDesignPatterns {  
    public static void main(String[] args) {
        Employee employee1 = new Employee("David", "Programmer", 1500);
        Employee employee2 = new Employee("Scott", "CEO", 3000);
        Employee employee3 = new Employee("Andrew", "Manager", 2000);
        Employee employee4 = new Employee("Scott", "Janitor", 500);
        Employee employee5 = new Employee("Juliette", "Marketing", 1000);
        Employee employee6 = new Employee("Rebecca", "Sales", 2000);
        Employee employee7 = new Employee("Chris", "Programmer", 1750);
        Employee employee8 = new Employee("Ivan", "Programmer", 1200);

        employee3.addCoworker(employee1);
        employee3.addCoworker(employee7);
        employee3.addCoworker(employee8);

        employee1.addCoworker(employee7);
        employee1.addCoworker(employee8);

        employee2.addCoworker(employee3);
        employee2.addCoworker(employee5);
        employee2.addCoworker(employee6);

        System.out.println(employee2);
        for (Employee headEmployee : employee2.getCoworkers()) {
            System.out.println(headEmployee);

            for(Employee employee : headEmployee.getCoworkers()) {
                System.out.println(employee);
            }
        }
    }
}
```

Several employees have been created here. The CEO has several employees as close employees, and some of them have their close employees in lower positions.

After all, senior employees are close employees of the CEO, and regular employees are colleagues of senior employees.

Running the above code will give:

```java
Employee : | Name: Scott, Position: CEO, Wage: 3000 |  
Employee : | Name: Andrew, Position: Manager, Wage: 2000 |  
Employee : | Name: David, Position: Programmer, Wage: 1500 |  
Employee : | Name: Chris, Position: Programmer, Wage: 1750 |  
Employee : | Name: Ivan, Position: Programmer, Wage: 1200 |  
Employee : | Name: Juliette, Position: Marketing, Wage: 1000 |  
Employee : | Name: Rebecca, Position: Sales, Wage: 2000 |  
```

# _Lab2_TMPS_Decorator_

At the beginning we will define the interface:

```java
public interface Computer {  
    void assemble();    
}
```

And by implementing this interface, we will define a class that we, using the Decorator template, will make subject to changes at runtime.:

```java
public class BasicComputer implements Computer {  
    @Override
    public void assemble() {
        System.out.print("Assembling a basic computer.");
    }
}
```

Now for the decorator class:

```java
public abstract class ComputerDecorator implements Computer {  
    protected Computer computer;

    public ComputerDecorator(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void assemble() {
        this.computer.assemble();
    }
}
```

Our specific classes will extend this class by inheriting its functionality and adding their own functions to the process.:
```java
public class GamingComputer extends ComputerDecorator {  
    public GamingComputer(Computer computer) {
        super(computer);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.print(" Adding characteristics of a gaming computer! ");
    }
}

public class WorkComputer extends ComputerDecorator {  
    public WorkComputer(Computer computer) {
        super(computer);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.print(" Adding characteristics of a work computer! ");
    }
}
```

When these specific classes are fully defined, we can observe the result:

```java
public class Main {  
    public static void main(String[] args) {
        Computer gamingComputer = new GamingComputer(new BasicComputer());
        gamingComputer.assemble();
        System.out.println("\n");

        Computer workComputer = new WorkComputer(new GamingComputer(new 
            BasicComputer()));
        workComputer.assemble();
    }
}
```

Running this code will give:

```java
Assembling a basic computer. Adding characteristics of a gaming computer! 

Assembling a basic computer. Adding characteristics of a gaming computer!  Adding characteristics of a work computer!  
```

# _Lab2_TMPS_Filter_

Let's start with the Employee class, which we will filter using various Criteria :

```java
public class Employee {  
    private String name;
    private String gender;
    private String position;

    public Employee(String name, String gender, String position) {
        this.name = name;
        this.gender = gender;
        this.position = position;
    }
}
```

The Criteria interface is quite simple, and all other specific criteria will implement its method in their own way:

```java
public interface Criteria {  
    public List<Employee> criteria(List<Employee> employeeList);
}
```

Having founded the filtering system, let's define several different criteria:

CriteriaMale - criteria for finding male employees
Criteria Female - criteria for finding female employees
Criteria Senior - criteria for finding senior employees
Criteria Junior - criteria for finding junior employees
And Criteria - a criterion for finding employees that meet two criteria
Or Criterion - a criterion for finding employees who meet one of the criteria that we apply


A simple for loop that adds all male employees to the list and returns it.

```java
public class CriteriaMale implements Criteria {

    @Override
    public List<Employee> criteria(List<Employee> employeeList) {
        List<Employee> maleEmployees = new ArrayList<>();

        for(Employee employee : employeeList) {
            if(employee.getGender().equalsIgnoreCase("Male")) {
                maleEmployees.add(employee);
            } 
        }
        return maleEmployees;
    }
}
```

A simple for loop that adds all female employees to the list and returns it.

```java
public class CriteriaFemale implements Criteria {

    @Override
    public List<Employee> criteria(List<Employee> employeeList) {
        List<Employee> femaleEmployees = new ArrayList<>();

        for(Employee employee : employeeList) {
            if(employee.getGender().equalsIgnoreCase("Female")) {
                femaleEmployees.add(employee);
            }
        }
        return femaleEmployees;
    }    
}
```

Similarly:

```java
public class CriteriaSenior implements Criteria{

    @Override
    public List<Employee> criteria(List<Employee> employeeList) {
         List<Employee> seniorEmployees = new ArrayList<>();

        for(Employee employee : employeeList) {
            if(employee.getPosition().equalsIgnoreCase("Senior")) {
                seniorEmployees.add(employee);
            }
        }
        return seniorEmployees;
    }    
}

public class CriteriaJunior implements Criteria {

    @Override
    public List<Employee> criteria(List<Employee> employeeList) {
                 List<Employee> juniorEmployees = new ArrayList<>();

        for(Employee employee : employeeList) {
            if(employee.getPosition().equalsIgnoreCase("Junior")) {
                juniorEmployees.add(employee);
            }
        }
        return juniorEmployees;
    } 
}
```

The list of employees is filtered by the first criteria, and then the already filtered list is filtered again by the second criteria.

```java
public class AndCriteria implements Criteria {

    private Criteria firstCriteria;
    private Criteria secondCriteria;

    public AndCriteria(Criteria firstCriteria, Criteria secondCriteria) {
        this.firstCriteria = firstCriteria;
        this.secondCriteria = secondCriteria;
    }

    @Override
    public List<Employee> criteria(List<Employee> employeeList) {
        List<Employee> firstCriteriaEmployees = firstCriteria.criteria(employeeList);
        return secondCriteria.criteria(firstCriteriaEmployees);
    }
}
```

Or according to one of the criteria:

```java
private Criteria firstCriteria;
    private Criteria secondCriteria;

    public OrCriteria(Criteria firstCriteria, Criteria secondCriteria) {
        this.firstCriteria = firstCriteria;
        this.secondCriteria = secondCriteria;
    }


    @Override
    public List<Employee> criteria(List<Employee> employeeList) {
        List<Employee> firstCriteriaEmployees = firstCriteria.criteria(employeeList);
        List<Employee> secondCriteriaEmployees = secondCriteria.criteria(employeeList);

        for (Employee employee : secondCriteriaEmployees) {
            if(!firstCriteriaEmployees.contains(employee)) {
                firstCriteriaEmployees.add(employee);
            }
        }
        return firstCriteriaEmployees;
    }
}
```

Now that all the Criteria implementations have been created, let's make a list of employees that will act as a list extracted from the database, and then fulfill a few criteria:

```java
public class Main {  
    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>();

        //adding employees to the list
        employeeList.add(new Employee("David", "Male", "Senior"));
        employeeList.add(new Employee("Scott", "Male", "Senior"));
        employeeList.add(new Employee("Rhett", "Male", "Junior"));
        employeeList.add(new Employee("Andrew", "Male", "Junior"));
        employeeList.add(new Employee("Susan", "Female", "Senior"));
        employeeList.add(new Employee("Rebecca", "Female", "Junior"));
        employeeList.add(new Employee("Mary", "Female", "Junior"));
        employeeList.add(new Employee("Juliette", "Female", "Senior"));
        employeeList.add(new Employee("Jessica", "Female", "Junior"));
        employeeList.add(new Employee("Mike", "Male", "Junior"));
        employeeList.add(new Employee("Chris", "Male", "Junior"));

        //initialization of the different criteria classes
        Criteria maleEmployees = new CriteriaMale();
        Criteria femaleEmployees = new CriteriaFemale();
        Criteria seniorEmployees = new CriteriaSenior();
        Criteria juniorEmployees = new CriteriaJunior();
        //AndCriteria and OrCriteria accept two Criteria as their constructor    
        arguments and return filtered lists
        Criteria seniorFemale = new AndCriteria(seniorEmployees, femaleEmployees);
        Criteria juniorOrMale = new OrCriteria(juniorEmployees, maleEmployees);

        System.out.println("Male employees: ");
        printEmployeeInfo(maleEmployees.criteria(employeeList));

        System.out.println("\nFemale employees: ");
        printEmployeeInfo(femaleEmployees.criteria(employeeList));

        System.out.println("\nSenior female employees: ");
        printEmployeeInfo(seniorFemale.criteria(employeeList));

        System.out.println("\nJunior or male employees: ");
        printEmployeeInfo(juniorOrMale.criteria(employeeList));
    }


    //simple method to print out employee info
    public static void printEmployeeInfo(List<Employee> employeeList) {
        for (Employee employee : employeeList) {
            System.out.println("Employee info: | Name: " 
                    + employee.getName() + ", Gender: " 
                    + employee.getGender() + ", Position: " 
                    + employee.getPosition() + " |");
        }
    }
}
```

Running this code will give:

```java
Male employees:  
Employee info: | Name: David, Gender: Male, Position: Senior |  
Employee info: | Name: Scott, Gender: Male, Position: Senior |  
Employee info: | Name: Rhett, Gender: Male, Position: Junior |  
Employee info: | Name: Andrew, Gender: Male, Position: Junior |  
Employee info: | Name: Mike, Gender: Male, Position: Junior |  
Employee info: | Name: Chris, Gender: Male, Position: Junior |

Female employees:  
Employee info: | Name: Susan, Gender: Female, Position: Senior |  
Employee info: | Name: Rebecca, Gender: Female, Position: Junior |  
Employee info: | Name: Mary, Gender: Female, Position: Junior |  
Employee info: | Name: Juliette, Gender: Female, Position: Senior |  
Employee info: | Name: Jessica, Gender: Female, Position: Junior |

Senior female employees:  
Employee info: | Name: Susan, Gender: Female, Position: Senior |  
Employee info: | Name: Juliette, Gender: Female, Position: Senior |

Junior or male employees:  
Employee info: | Name: Rhett, Gender: Male, Position: Junior |  
Employee info: | Name: Andrew, Gender: Male, Position: Junior |  
Employee info: | Name: Rebecca, Gender: Female, Position: Junior |  
Employee info: | Name: Mary, Gender: Female, Position: Junior |  
Employee info: | Name: Jessica, Gender: Female, Position: Junior |  
Employee info: | Name: Mike, Gender: Male, Position: Junior |  
Employee info: | Name: Chris, Gender: Male, Position: Junior |  
Employee info: | Name: David, Gender: Male, Position: Senior |  
Employee info: | Name: Scott, Gender: Male, Position: Senior |  
```
