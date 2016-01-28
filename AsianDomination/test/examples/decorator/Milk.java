package examples.decorator;

public class Milk extends CondimentDecorator {
	double bev;
	Beverage beverage;
	String cheese;

	public Milk(Beverage beverage) {
		this.beverage = beverage;
	}

	public String getDescription() {
		return beverage.getDescription() + ", Milk";
	}

	public double cost() {
		return .10 + beverage.cost();
	}
}
