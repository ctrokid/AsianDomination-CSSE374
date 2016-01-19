package examples.simple;

public class Cat extends Animal {
	private Fur fur;

	@Override
	public String getNoise(int length) {
		return fur.getColor();
	}
	
	
}
