package examples.singleton;
/**
 * Bad singleton, no static getInstance method
 * @author yangr
 *
 */
public class BadSingleton2 {
	private static volatile SingletonExample s;

	private BadSingleton2() {

	};

	public static SingletonExample getInstance() {
		synchronized (s) {
			if (s == null) {
				s = SingletonExample.getInstance();
			}
		}
		return s;
	}
}
