package gearCalc;

public class Weapon extends Gear {

	public Weapon(String csv) {
		super(csv);
	}
	
	@Override
	public String getType() {
		return "W";
	}
	
	@Override
	public String toString() {
		return "Weapon | " + super.toString();
	}
}
