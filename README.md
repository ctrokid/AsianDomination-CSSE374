# AsianDomination-CSSE374

## Design of the tool:
###Milestone 1 
We just used one visitor pattern to visit each TargetClass. After they were visited, we used a VisitCollection method to print the inheritance/interface relationships.

###Milestone 2
We added a RelationshipManager class to be the second visitor that happens once the classes are printed. This got rid of the VisitCollection method from Milestone 1, as the RelationshipManager can handle printing all relationships (Inheritance, Interface, Association, Uses).

## Tasks Log:
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

##Instruction of Running Code:
In order to generate a PDF of code, user needs to go to DesignParser class and direct to the correct source directory for sourceDir. The directory can be found in property of the package. User can also name the names of output files in MAIN under asmOutputPath and dotOutputPath. The very last line of DesignParser class launch the Dot application and generate the UML PDF. Once the classes ran, the PDF can be accessed under input_output folder. 
