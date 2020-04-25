package gearCalc;

public class Necklace extends Gear {

	public Necklace(String csv) {
		super(csv);
	}
	
	@Override
	public String getType() {
		return "N";
	}
	
@Override
	public String toString() {
		return "Necklace | " + super.toString();
	}

}
