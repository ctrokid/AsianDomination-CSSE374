package SequenceDiagramPack;

public class driver {
	public static void main(String[] args) {
		Sale s = new Sale();
		Register r = new Register(s);
		double a = 10.0;
		r.checkout(a);
	}
}
