package examples.adapter;

public class WeirdAdapter implements ITarget {
	private Adaptee getAdaptee() {
		return new Adaptee();
	}
	
	@Override
	public void method1() {
		getAdaptee().m1();
	}

	@Override
	public void method2() {
		getAdaptee().m2();
		getAdaptee().m3();
	}

}
