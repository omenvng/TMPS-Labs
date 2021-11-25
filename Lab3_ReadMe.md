# Laboratory work No. 3
_FAF-193 , Vangheli Dumitru_

## Task💠
Целью данной лабораторной работы было имплементировать 5 паттернов.

## Patterns used📓
1. _Iterator_
2. _MVC_
3. _Strategy_
4. _TemplateMethod_
5. _Visitor_

## _Iterator_
![Iterator](https://refactoring.guru/images/patterns/cards/iterator-mini-2x.png)

An iterator is a behavioral design pattern that makes it possible to consistently bypass the elements of composite objects without revealing their internal representation.

The idea of the Iterator pattern is to take the behavior of traversing a collection from the collection itself into a separate class.
![Iterator](https://refactoring.guru/images/patterns/diagrams/iterator/solution1-2x.png)

The iterator gives us the opportunity to implement different passes through the sheet
## _MVC_

The MVC pattern stands for Model-View-Controller Pattern. This template is used to separate application issues.

Model - A model represents an object or JAVA POJO carrying data. It can also have logic to update the controller if its data changes.

View - A view is a visualization of the data contained in the model.

Controller - The controller acts on both the model and the view. It controls the flow of data into the model object and updates the view every time the data changes. It keeps the view and model separate.

## _Strategy_

In the Strategy pattern, the behavior of a class or its algorithm can be changed at runtime. This type of design pattern falls under the behavior model.

In the Strategy pattern, we create objects representing different strategies and a context object whose behavior varies depending on the strategy object. The strategy object modifies the execution algorithm of the context object.


## _TemplateMethod_

In the Template Method, an abstract class provides a specific method(s)/template(s) for executing its methods. Its subclasses can override the implementation of the method as needed, but the call must be the same as defined by the abstract class. This pattern belongs to the category of behavior patterns.
## _Visitor_

In the Visitor template, we use the visitor class, which modifies the algorithm for executing the element class. Thus, the algorithm for executing an element can change how and when a visitor changes. This pattern belongs to the category of behavior patterns. According to the template, the element object must accept a visitor object so that the visitor object performs an operation on the element object.


# _Lab3_TMPS_Iterator_

Creating interfaces:

```java
public interface Iterator {
   public boolean hasNext();
   public Object next();
}

public interface Container {
   public Iterator getIterator();
}
```

Creating a specific class that implements the Container interface. This class has an internal NameIterator class that implements the Iterator interface.

```java
public class NameRepository implements Container {
   public String names[] = {"Robert" , "John" ,"Julie" , "Lora"};

   @Override
   public Iterator getIterator() {
      return new NameIterator();
   }

   private class NameIterator implements Iterator {

      int index;

      @Override
      public boolean hasNext() {
      
         if(index < names.length){
            return true;
         }
         return false;
      }

      @Override
      public Object next() {
      
         if(this.hasNext()){
            return names[index++];
         }
         return null;
      }		
   }
}
```

We use the Name Repository to get the iterator and output the names:

```java
public class IteratorPatternDemo {
	
   public static void main(String[] args) {
      NameRepository namesRepository = new NameRepository();

      for(Iterator iter = namesRepository.getIterator(); iter.hasNext();){
         String name = (String)iter.next();
         System.out.println("Name : " + name);
      } 	
   }
}
```

Running this code:

```java
Name : Robert
Name : John
Name : Julie
Name : Lora
```

# _Lab3_TMPS_MVC_

Creating a model.

```java
public class Student {
   private String rollNo;
   private String name;
   
   public String getRollNo() {
      return rollNo;
   }
   
   public void setRollNo(String rollNo) {
      this.rollNo = rollNo;
   }
   
   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
}
```

Creating a view:

```java
public class StudentView {
   public void printStudentDetails(String studentName, String studentRollNo){
      System.out.println("Student: ");
      System.out.println("Name: " + studentName);
      System.out.println("Roll No: " + studentRollNo);
   }
}
```

Creating a controller:

```java
public class StudentController {
   private Student model;
   private StudentView view;

   public StudentController(Student model, StudentView view){
      this.model = model;
      this.view = view;
   }

   public void setStudentName(String name){
      model.setName(name);		
   }

   public String getStudentName(){
      return model.getName();		
   }

   public void setStudentRollNo(String rollNo){
      model.setRollNo(rollNo);		
   }

   public String getStudentRollNo(){
      return model.getRollNo();		
   }

   public void updateView(){				
      view.printStudentDetails(model.getName(), model.getRollNo());
   }	
}
```

We use the StudentController methods to demonstrate the use of the MVC design pattern.

```java
public class MVCPatternDemo {
   public static void main(String[] args) {

      //fetch student record based on his roll no from the database
      Student model  = retriveStudentFromDatabase();

      //Create a view : to write student details on console
      StudentView view = new StudentView();

      StudentController controller = new StudentController(model, view);

      controller.updateView();

      //update model data
      controller.setStudentName("John");

      controller.updateView();
   }

   private static Student retriveStudentFromDatabase(){
      Student student = new Student();
      student.setName("Robert");
      student.setRollNo("10");
      return student;
   }
}
```

After running the code, we get the following:

```java
Student: 
Name: Robert
Roll No: 10
Student: 
Name: John
Roll No: 10
```

# _Lab3_TMPS_Strategy_

Creating an interface.

```java
public interface Strategy {
   public int doOperation(int num1, int num2);
}
```

Creating specific classes that implement the same interface.

```java
public class OperationAdd implements Strategy{
   @Override
   public int doOperation(int num1, int num2) {
      return num1 + num2;
   }
}

public class OperationSubstract implements Strategy{
   @Override
   public int doOperation(int num1, int num2) {
      return num1 - num2;
   }
}

public class OperationMultiply implements Strategy{
   @Override
   public int doOperation(int num1, int num2) {
      return num1 * num2;
   }
}
```

Creating a context class.

```java
public class Context {
   private Strategy strategy;

   public Context(Strategy strategy){
      this.strategy = strategy;
   }

   public int executeStrategy(int num1, int num2){
      return strategy.doOperation(num1, num2);
   }
}
```

We use context to see changes in behavior when he changes his strategy.

```java
public class StrategyPatternDemo {
   public static void main(String[] args) {
      Context context = new Context(new OperationAdd());		
      System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

      context = new Context(new OperationSubstract());		
      System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

      context = new Context(new OperationMultiply());		
      System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
   }
}
```

After running the code, we get the following

```java
10 + 5 = 15
10 - 5 = 5
10 * 5 = 50
```

# _Lab3_TMPS_TemplateMethod_

Creating an abstract class with a template method that is final.
```java
public abstract class Game {
   abstract void initialize();
   abstract void startPlay();
   abstract void endPlay();
   
   public final void play(){
      initialize();
      startPlay();
      endPlay();
   }
}
```

Creating specific classes that extend the above class.

```java
public class Cricket extends Game {

   @Override
   void endPlay() {
      System.out.println("Cricket Game Finished!");
   }

   @Override
   void initialize() {
      System.out.println("Cricket Game Initialized! Start playing.");
   }

   @Override
   void startPlay() {
      System.out.println("Cricket Game Started. Enjoy the game!");
   }
}

public class Football extends Game {

   @Override
   void endPlay() {
      System.out.println("Football Game Finished!");
   }

   @Override
   void initialize() {
      System.out.println("Football Game Initialized! Start playing.");
   }

   @Override
   void startPlay() {
      System.out.println("Football Game Started. Enjoy the game!");
   }
}
```

We use the template method Game play () to demonstrate a certain way of playing.

```java
public class TemplatePatternDemo {
   public static void main(String[] args) {

      Game game = new Cricket();
      game.play();
      System.out.println();
      game = new Football();
      game.play();		
   }
}
```

After running the code, we get the following

```java
Cricket Game Initialized! Start playing.
Cricket Game Started. Enjoy the game!
Cricket Game Finished!

Football Game Initialized! Start playing.
Football Game Started. Enjoy the game!
Football Game Finished!
```

# _Lab3_TMPS_Visitor_

Defining the interface to represent the element.

```java
public interface ComputerPart {
   public void accept(ComputerPartVisitor computerPartVisitor);
}
```

Creating specific classes that extend the above interface.

```java
public class Keyboard implements ComputerPart {

   @Override
   public void accept(ComputerPartVisitor computerPartVisitor) {
      computerPartVisitor.visit(this);
   }
}

public class Monitor implements ComputerPart {

   @Override
   public void accept(ComputerPartVisitor computerPartVisitor) {
      computerPartVisitor.visit(this);
   }
}

public class Mouse implements ComputerPart {

   @Override
   public void accept(ComputerPartVisitor computerPartVisitor) {
      computerPartVisitor.visit(this);
   }
}

public class Computer implements ComputerPart {
	
   ComputerPart[] parts;

   public Computer(){
      parts = new ComputerPart[] {new Mouse(), new Keyboard(), new Monitor()};		
   } 


   @Override
   public void accept(ComputerPartVisitor computerPartVisitor) {
      for (int i = 0; i < parts.length; i++) {
         parts[i].accept(computerPartVisitor);
      }
      computerPartVisitor.visit(this);
   }
}
```

Defining the interface to represent the visitor.

```java
public interface ComputerPartVisitor {
	public void visit(Computer computer);
	public void visit(Mouse mouse);
	public void visit(Keyboard keyboard);
	public void visit(Monitor monitor);
}
```

Creating a specific visitor implementing the above class.

```java
public class ComputerPartDisplayVisitor implements ComputerPartVisitor {

   @Override
   public void visit(Computer computer) {
      System.out.println("Displaying Computer.");
   }

   @Override
   public void visit(Mouse mouse) {
      System.out.println("Displaying Mouse.");
   }

   @Override
   public void visit(Keyboard keyboard) {
      System.out.println("Displaying Keyboard.");
   }

   @Override
   public void visit(Monitor monitor) {
      System.out.println("Displaying Monitor.");
   }
}
```

We use ComputerPartDisplayVisitor to display parts of the computer

```java
public class VisitorPatternDemo {
   public static void main(String[] args) {

      ComputerPart computer = new Computer();
      computer.accept(new ComputerPartDisplayVisitor());
   }
}
```

After running the code, we get the following.

```java
Displaying Mouse.
Displaying Keyboard.
Displaying Monitor.
Displaying Computer.
```
