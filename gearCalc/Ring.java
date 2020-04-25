package gearCalc;

public class Ring extends Gear {

	public Ring(String csv) {
		super(csv);
	}
	
	@Override
	public String getType() {
		return "R";
	}
	
	@Override
	public String toString() {
		return "Ring | " + super.toString();
	}
}
