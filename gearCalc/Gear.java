package gearCalc;

import java.util.HashMap;

public abstract class Gear {
	private HashMap<String, Integer> statTable;
	private String set;
	private int gearScore;
	private int iLvl;
	
	public Gear(String csv) {
		statTable = new HashMap<>();
		generateGear(csv);
	}
	
	public HashMap<String, Integer> getStats(){
		return statTable;
	}
	
	public void generateGear(String csv) {
		String[] split = csv.split(",");
		iLvl = Integer.valueOf(split[1]);
		statTable.put(split[3], Integer.valueOf(split[4]));
		String[] stats = new String[] {"HP%", "Def%", "Res", "Atk%", "CCh",	"CDmg", "Spd", "Eff", "HP", "Atk", "Def"};
		for(int i = 5; i < 16; i++) {
			if(!(split[i].equals(""))) {
				statTable.put(stats[i-5], Integer.valueOf(split[i]));
			}
		}
		set = split[16];
		gearScore = Integer.valueOf(split[19]); 
	}
	
	public String getType() {
		return "G";
	}
	
	public String getSet() {
		return set;
	}
	
	public int getGearScore() {
		return gearScore;
	}
	
	@Override
	public String toString() {
		String stats = "";
		for(String key : statTable.keySet()) {
			stats += key + ": " + statTable.get(key) + " | ";
		}
		return "iLvl: " + iLvl + " | " + stats + set + " | " + gearScore;
	}
}
