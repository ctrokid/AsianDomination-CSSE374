package AdapterDummieClasses;

public class Adapter implements ITarget {
	Adaptee a = new Adaptee();

	@Override
	public void method1() {
		a.m1();
		
	}

	@Override
	public void method2() {
		a.m2();
		a.m3();
		
	}
	
}
