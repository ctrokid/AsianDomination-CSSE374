package examples.composite;

import java.util.ArrayList;
import java.util.List;

public class MutiParamComposite extends MutiparamComponent {
	private List<MutiparamComponent> components;
	
	public MutiParamComposite() {
		components = new ArrayList<MutiparamComponent>();
	}

	@Override
	public void add(MutiparamComponent comp, int i) {
		components.add(comp);
	}
	
	@Override
	public void remove(MutiparamComponent comp, int i) {
		components.remove(comp);
	}

	@Override
	public void method1() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void method2() {
		// TODO Auto-generated method stub
		
	}
	
}
