package examples.singleton;

public class SingletonExampleWithoutGetInstance {
	private static volatile SingletonExampleWithoutGetInstance s;

	private SingletonExampleWithoutGetInstance() {

	};

	public static SingletonExampleWithoutGetInstance getIt() {
		synchronized (s) {
			if (s == null) {
				s = new SingletonExampleWithoutGetInstance();
			}
		}
		return s;
	}
}
