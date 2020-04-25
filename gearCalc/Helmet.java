package gearCalc;

public class Helmet extends Gear {
	
	public Helmet(String csv) {
		super(csv);
	}
	
	@Override
	public String getType() {
		return "H";
	}
	@Override
	public String toString() {
		return "Helmet | " + super.toString();
	}
}
