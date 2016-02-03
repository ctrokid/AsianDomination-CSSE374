package examples.decorator;

public class NotImplementingClass {
	Beverage bev;

	public NotImplementingClass() {
		bev = new Beverage() {

			@Override
			public double cost() {
				// TODO Auto-generated method stub
				return 0;
			}
		};
	}

}
