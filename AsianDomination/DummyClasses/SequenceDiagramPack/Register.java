package SequenceDiagramPack;

public class Register {
	private Sale sale;
	public Register(Sale sale){
		this.sale = sale;
	}
	public void checkout(int cashTendered) {
		// @TA:
		// 1. It is ok if students created a different method instead of
		// checkout(int)
		// 2. It is also ok if the method did not have a parameter but student
		// must pass
		// an int argument when calling makePayment
		sale.makePayment(cashTendered);
		out();
	}
	
	private void out() {
		
	}
}
