# AsianDomination-CSSE374

## Design of the tool:
---
###Milestone 1 
We just used one visitor pattern to visit each TargetClass. After they were visited, we used a VisitCollection method to print the inheritance/interface relationships. 

Milestone 1 UML Diagram 
![milestone1uml](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M1/Milestone1UML.jpg)

###Milestone 2
We added a RelationshipManager class to be the second visitor that happens once the classes are printed. This got rid of the VisitCollection method from Milestone 1, as the RelationshipManager can handle printing all relationships (Inheritance, Interface, Association, Uses). 

Milestone 2 UML Diagram
![milestone2uml](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M2/Milestone2UML.jpg)

###Milestone 3
We added MethodStatement class to store the relationship between classes for sequence diagram and SequenceDiagramMethodVisitor and SequenceDiagramTraverser class to recursively added relationships to generate sequence diagram. 

Milestone 3 UML Diagram
![miletone3uml](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M3/Milestone3UML.jpg)


###Milestone 4
Before writing the logic for milestone 4, we decided to refactor our design in a large manner. We added two strategy patterns: one for building the model, and one for writing it out. These strategies combined with an abstract InputCommand class act as a form template method design pattern. The project model uses the strategies from the InputCommand to do the program execution (Building/Writing). This allowed us to implement the following minimal changes for Singleton detection.

We extended the UmlDiagramOutputStream, then added a singleton detection method. It checks the TargetClass before it is printed out. If it passes the singleton test, then it is printed out differently. We are still working on singleton that is based on abstractFactory design pattern.

Milestone 4 UML Diagram
![milestone4uml](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M4/Milestone4UML.jpg)


###Milestone 5
For this milestone, since we are dectecting adapter and decorator patterns, we added decorators for both pattern and modified Targetclass to detect the patterns. 

We refactored the code so the ITarget class can recognize different patterns. The client side can simply modify three classes in out application to achieve detect a new pattern. 

Milestone 5 UML Diagram
![milestone5uml](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M5/MilestoneManual5UML.png)

##Milestone Deliverables
---
###Milestone 1
######Lab 1-3 UML Diagrams
[Manual UML Diagram for Lab 1-3](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M1/M1lab1-3ManualUML.PNG)

[Generated UML Diagram for Lab 1-3](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M1/M1lab1-3GeneratedUML.pdf)

######Project UML Diagrams
[Manual UML Diagram for Project](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M1/Milestone1UML.jpg)

[Generated UML Diagram for Project](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M1/M1projectGeneratedUML.pdf)

###Milestone 2
######Abstract Factory PizzaStore
[Generated UML for Abstract Factory PizzaStore](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M2/M2AbstractFactoryPizzaStoreUML.pdf)

######Project UML Diagrams
[Manual UML Diagram for Project](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M2/Milestone2UML.jpg)

[Generated UML Diagram for Project](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M2/M2ProjectGeneratedUML.pdf)

###Milestone 3
######Collection Sequence Diagrams
[Manual Sequence Diagram for Collection](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M3/M3CollectionManualSequence.png)

[Generated Sequence Diagram for Collection](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/demo_diagrams/M3CollectionsSD.PNG)

######Project Sequence Diagrams
[Manual Sequence Diagram for Project](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M3/M3ProjectManualSequence.jpg)

[Generated Sequence Diagram for Project](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/demo_diagrams/ProjectSD.PNG)

###Milestone 4
######Singleton Java Classes
[Singleton Detacting Java Classes](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M4/AutomatedSingletonTest.png)

[Chocolate Boiler](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M4/SingletonTest.png)

[Milestone 4 UML Diagram](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M4/Milestone4UML.jpg)

###Milestone 5
######In Class Lab Detecting
[Lab2-1](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/demo_diagrams/M5Lab2.png)

[Lab5-1]

[Java Classes]

[Generated Project UML](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M5/Milestone5-GeneratedUML.png)

## Tasks Log:
---
12/15/2015
All members met for designing milestone 1. 
1h

12/17/2015
Collin & Coco implemented all the classes from the design

12/18/2015
Runzi & Collin added Dot utils.

01/05/2015
All members pair programmed the rest of the implementation and test.

01/06/2015
All members pair programmed milestone 2 for 6 hours.

01/10/2016
All members met up for 3 hours to refactor Milestone 2.

01/11/2016
All members met up for 2 hours to come up designs for Milestone 3.

01/13/2016
All members met up for 10 hours attempting to finish Milestone 3. But we didn't .....

01/15/2016
All members met up for 30 minutes to talk aobut new design.

01/17/2016
All members met up for 3 hours to redesign the entire project.

01/18/2016
All members met up for 2 hours and started refactor and code for the new design.

01/19/2016
All members met up for 3.5 hours continue working on refactoring and fixing bugs in the project.

01/20/2016
All members met up for 5.5 hours to finish sequence diagram (milestone 3) and finished milestone 4.

01/24/2016
All members met for 2.5 hours to refactor current code

01/25/2016
All members met for 3 hours to redesign milestone 5 and started implementation.

01/26/2016
All members met for 3 hours to add necessary classes for adapter and decorator.

01/27/2016
All members met for 7 hours to finish up milestone 5. 

##Instruction of Running Code:
---
Current by running main, the project will generate all the milestone's images to input_output folder. The command line is currently not functional, will be functional later on in the project milestones.
