package examples.composite;

import java.util.ArrayList;
import java.util.List;

public class MyComposite extends MyComponent {
	private List<MyComponent> components;
	
	public MyComposite() {
		components = new ArrayList<MyComponent>();
	}
	
	@Override
	public void method1() {

	}

	@Override
	public void method2() {

	}

	@Override
	public void add(MyComponent comp) {
		components.add(comp);
	}
	
	@Override
	public void remove(MyComponent comp) {
		components.remove(comp);
	}
	
}
