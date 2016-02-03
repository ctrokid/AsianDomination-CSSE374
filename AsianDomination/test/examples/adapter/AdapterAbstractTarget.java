package examples.adapter;

public class AdapterAbstractTarget extends AbstractTarget {

	private Adaptee a = new Adaptee();
	
	@Override
	public void method1() {
		a.m1();
	}

	@Override
	public void method2() {
		a.m2();
	}

}
