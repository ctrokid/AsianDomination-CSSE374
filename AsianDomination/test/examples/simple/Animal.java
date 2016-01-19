package examples.simple;

public abstract class Animal implements Runnable {
	protected String name;
	private int age;
	public double var;
	
	public abstract String getNoise(int length);
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
