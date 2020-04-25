package gearCalc;

public class Chest extends Gear {

	public Chest(String csv) {
		super(csv);
	}
	
	@Override
	public String getType() {
		return "C";
	}
	
	@Override
	public String toString() {
		return "Chest | " + super.toString();
	}
}
