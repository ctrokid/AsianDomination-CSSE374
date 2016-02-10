package examples.composite;

import java.util.Collection;

public class WeirdComposite1 extends MyComposite {
	private Collection<WeirdComponent1> list;
	
	@Override
	public void add(MyComponent comp) {
		list.add(comp);
	}

	@Override
	public void remove(MyComponent comp) {
		list.remove(comp);
	}
}
