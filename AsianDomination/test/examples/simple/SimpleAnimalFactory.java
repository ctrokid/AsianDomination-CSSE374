package examples.simple;

public class SimpleAnimalFactory {
	
	public Animal createAnimal(String animal) {
		Animal a = null;
		
		if (animal.equals("dog"))
			a = new Dog();
		else if (animal.equals("cat"))
			a = new Cat();
		
		return a;
	}
}
