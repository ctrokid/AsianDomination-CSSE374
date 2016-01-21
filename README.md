# AsianDomination-CSSE374

## Design of the tool:
---
###Milestone 1 
We just used one visitor pattern to visit each TargetClass. After they were visited, we used a VisitCollection method to print the inheritance/interface relationships. 

Milestone 1 UML Diagram 
![milestone1uml](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/Milestone1UML.jpg)

###Milestone 2
We added a RelationshipManager class to be the second visitor that happens once the classes are printed. This got rid of the VisitCollection method from Milestone 1, as the RelationshipManager can handle printing all relationships (Inheritance, Interface, Association, Uses). 

Milestone 2 UML Diagram
![milestone2uml](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/Milestone2UML.jpg)

###Milestone 3
We added MethodStatement class to store the relationship between classes for sequence diagram and SequenceDiagramMethodVisitor and SequenceDiagramTraverser class to recursively added relationships to generate sequence diagram. 

Milestone 3 UML Diagram
![miletone3uml](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/Milestone3UML.jpg)



###Milestone 4
We extended the old umldiagramoutputstream, then add a singleton detection method. It checks the TargetClass before it is printed out. If it passes the singleton test, then it is printed out differently. We are still working on singleton that is based on abstracFactory design pattern.

Milestone 4UML Diagram
![milestone4uml](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/Milestone4UML.jpg)

##Milestone Deliverables
---
###Milestone 1
######Lab 1-3 UML Diagrams
[Manual UML Diagram for Lab 1-3](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M1lab1-3ManualUML.PNG)

[Generated UML Diagram for Lab 1-3](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M1lab1-3GeneratedUML.pdf)

######Project UML Diagrams
[Manual UML Diagram for Project](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/Milestone1UML.jpg)

[Generated UML Diagram for Project](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M1projectGeneratedUML.pdf)

###Milestone 2
######Abstract Factory PizzaStore
[Generated UML for Abstract Factory PizzaStore](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M2AbstractFactoryPizzaStoreUML.pdf)

######Project UML Diagrams
[Manual UML Diagram for Project](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/Milestone2UML.jpg)

[Generated UML Diagram for Project](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M2ProjectGeneratedUML.pdf)

###Milestone 3
######Collection Sequence Diagrams
[Manual Sequence Diagram for Collection](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M3CollectionManualSequence.png)

[Generated Sequence Diagram for Collection](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/demo_diagrams/M3CollectionsSD.PNG)

######Project Sequence Diagrams
[Manual Sequence Diagram for Project](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M3ProjectManualSequence.jpg)

[Generated Sequence Diagram for Project](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/demo_diagrams/ProjectSD.PNG)


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


##Instruction of Running Code:
---
Current by running main, the project will generate all the milestone's images to input_output folder. The command line is currently not functional, will be functional later on in the project milestones.
