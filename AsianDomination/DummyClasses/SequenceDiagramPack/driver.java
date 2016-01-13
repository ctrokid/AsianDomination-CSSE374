package SequenceDiagramPack;

public class driver {
	public static void main(String[] args) {
		Sale s = new Sale();
		Register r = new Register(s);
		r.checkout(10);
	}
}
