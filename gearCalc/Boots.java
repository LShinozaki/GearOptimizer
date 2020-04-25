package gearCalc;

public class Boots extends Gear {

	public Boots(String csv) {
		super(csv);
	}
	
	@Override
	public String getType() {
		return "B";
	}
	
	@Override
	public String toString() {
		return "Boots | " + super.toString();
	}
}
