package examples.adapter;

// 75% method delegation threshold
public class DelegationAdapter implements IDelegationTarget {
	Adaptee a = new Adaptee();
	
	@Override
	public int getStats() {
		a.m1();
		return 0;
	}

	@Override
	public void setStats() {
		// TODO Auto-generated method stub
		a.m2();
	}

	@Override
	public boolean isValid() {
		a.m1();
		return false;
	}

	@Override
	public void setThis() {
//		a.m3();
	}
	
}
