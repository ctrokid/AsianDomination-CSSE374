package examples.composite;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class MutiparamComponent {
	public abstract void method1();
	public abstract void method2();
	
	public void add(MutiparamComponent comp, int i) {
		throw new NotImplementedException();
	}
	
	public void remove(MutiparamComponent comp, int i) {
		throw new NotImplementedException();
	}
}
