package examples.simple;

public class SingletonExample {
	private static volatile SingletonExample s;
	private SingletonExample(){
		
	};
	
	public static SingletonExample getInstance(){
		synchronized (s) {
			if(s == null){
				s = new SingletonExample();
			}
		}
		return s;
	}
}
