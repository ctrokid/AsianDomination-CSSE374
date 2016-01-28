package examples.singleton;
/**
 * Bad singleton, no static getInstance method
 * @author yangr
 *
 */
public class BadSingleton1 {
	private static volatile BadSingleton1 s;

	private BadSingleton1() {

	};

	public BadSingleton1 getInstance() {
		synchronized (s) {
			if (s == null) {
				s = new BadSingleton1();
			}
		}
		return s;
	}
}
