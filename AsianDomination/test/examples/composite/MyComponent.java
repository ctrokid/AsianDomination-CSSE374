package examples.composite;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class MyComponent {
	public abstract void method1();
	public abstract void method2();
	
	public void add(MyComponent comp) {
		throw new NotImplementedException();
	}
	
	public void remove(MyComponent comp) {
		throw new NotImplementedException();
	}
}
