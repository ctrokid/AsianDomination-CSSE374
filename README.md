# AsianDomination-CSSE374

## Design of the tool:
---
###Milestone 1 
We just used one visitor pattern to visit each TargetClass. After they were visited, we used a VisitCollection method to print the inheritance/interface relationships. See [Milestone 1 UML Diagram] (https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M1ManualUML.pdf) for more specific design. 

###Milestone 2
We added a RelationshipManager class to be the second visitor that happens once the classes are printed. This got rid of the VisitCollection method from Milestone 1, as the RelationshipManager can handle printing all relationships (Inheritance, Interface, Association, Uses). See [Milestone 2 UML Diagram] (https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/CurrentManualUML.pdf) for more specific design.

###Milestone 3
We added MethodStatement class to store the relationship between classes for sequence diagram and SequenceDiagramMethodVisitor and SequenceDiagramTraverser class to recursively added relationships to generate sequence diagram. See [Milestone 3 UML Diagram]() for more specific design. **This is not the desired design for this milestone. We will need to refactor the code and come up with a better design.**

##Milestone Deliverables
---
###Milestone 1
######Lab 1-3 UML Diagrams
[Manual UML Diagram for Lab 1-3](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M1lab1-3ManualUML.PNG)

[Generated UML Diagram for Lab 1-3](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M1lab1-3GeneratedUML.pdf)

######Project UML Diagrams
[Manual UML Diagram for Project](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M1ManualUML.pdf)

[Generated UML Diagram for Project](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M1projectGeneratedUML.pdf)

###Milestone 2
######Abstract Factory PizzaStore
[Generated UML for Abstract Factory PizzaStore](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M2AbstractFactoryPizzaStoreUML.pdf)

######Project UML Diagrams
[Manual UML Diagram for Project](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/CurrentManualUML.pdf)

[Generated UML Diagram for Project](https://github.com/ctrokid/AsianDomination-CSSE374/blob/master/AsianDomination/docs/M2ProjectGeneratedUML.pdf)

###Milestone 3
**TODO: Add links for the following diagrams**
######Collection Sequence Diagrams
[Manual Sequence Diagram for Collection]()

[Generated Sequence Diagram for Collection]()

######Project Sequence Diagrams
[Manual Sequence Diagram for Project]()

[Generated Sequence Diagram for Project]()


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

01/18/2016
All members met up for 2 hours to come up designs for Milestone 3.

01/20/2016
All members met up for 10 hours attempting to finish Milestone 3. But we didn't .....

##Instruction of Running Code:
---
By running DesignParser class, the concole will require user input to generate UMl or Sequence diagram. The input should be as shown below:
UML Diagram example: "UML, package path or classes names" 
Squence Diagram example: "SEQ, package path or classes names, initial class name, method name, depth (default 5)" 
Once the classes ran, the PDF can be accessed under input_output folder. 

##TODO List for Asians
---
- [ ] Add Pizza class to Abstract Pizza Factory 
- [ ] Update Milestone 3 UML Diagram
- [ ] Add link for Milestone 3 deliverables
- [ ] 


