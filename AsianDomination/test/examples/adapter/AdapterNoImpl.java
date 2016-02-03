package examples.adapter;

public class AdapterNoImpl implements ITarget {
	private Adaptee a = new Adaptee();
	
	@Override
	public void method1() {
		a.m1();
	}

	@Override
	public void method2() {

	}

}
