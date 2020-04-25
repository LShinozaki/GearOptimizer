package gearCalc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GearSet {
	public Gear[] gearSet;
	private StatTable statTable;
	private ArrayList<String> setList;
	
	public GearSet() {
		gearSet = new Gear[6];
		statTable = new StatTable();
		setList = new ArrayList<>();
	}
	
	public void equip(Gear gear) {
		String[] helper = new String [] {"W", "H", "C", "N", "R", "B"};
		String type = gear.getType();
		for(int i = 0; i < helper.length; i++) {
			if(helper[i].equals(type)) {
				gearSet[i] = gear;
				clearStats();
			}
		}	
	}
	
	public void calcStats() {
		for(Gear gear : gearSet) {
			if(gear != null) {
				statTable.update(gear.getStats());
				setList.add(gear.getSet());
			}
		}
		completeSet();
	}
	
	public void completeSet() {
		ArrayList<String> completeSetBonus = new ArrayList<>();
		String[] fourSet = new String[] {"Spd", "Atk", "LS", "Dest", "Cntr", "Rage"};
		for(int i = 0; i < fourSet.length; i++) {
			int counter = Collections.frequency(setList, fourSet[i]);
			if (counter >= 4) {
				completeSetBonus.add(fourSet[i]);
				break;
			}
		}
		
		String[] twoSet = new String[] {"Imm", "Crit", "Res", "Hit", "HP", "Def", "Unity"};
		for(int i = 0; i < twoSet.length; i++) {
			int counter = Collections.frequency(setList, twoSet[i]);
			for(int j = 0; j < (counter/2); j++) {
				completeSetBonus.add(twoSet[i]);
			}
		}
		if(!completeSetBonus.isEmpty()) {
			for(int i = 0; i < completeSetBonus.size(); i++) {
				setBonusCalc(completeSetBonus.get(i));
				setList = completeSetBonus;
			}
		} else {
			setList.clear();
		}
	}
	
	public void setBonusCalc(String set) {
		if(set.equals("Atk")) {
			statTable.update("Atk%", 35);
		}
		if(set.equals("Dest")) {
			statTable.update("CDmg", 40);
		}
		if(set.equals("Crit")) {
			statTable.update("CCh", 12);
		}
		if(set.equals("HP")) {
			statTable.update("HP%", 15);
		}
		if(set.equals("Def")) {
			statTable.update("Def%", 15);
		}
		if(set.equals("Res")) {
			statTable.update("Res", 20);
		}
		if(set.equals("Hit")) {
			statTable.update("Eff", 20);
		}
		if(set.equals("Unity")) {
			statTable.update("Dual", 4);
		}
	}
	
	public boolean isComplete() {
		for(int i = 0; i < gearSet.length; i++) {
			if(gearSet[i] == null) {
				return false;
			}
		}
		return true;
	}
	
	public void clearStats() {
		setList.clear();
		statTable = new StatTable();
	}
	public int avgGearScore() {
		int sum = 0;
		for (Gear gear : gearSet) {
			if(gear != null) {
				sum += gear.getGearScore();
			}
		}
		return (int) ((double) sum / gearSet.length);
	}
	
	public ArrayList<String> getSetList(){
		return setList;
	}
	
	public int get(String key) {
		return statTable.get(key);
	}
	
	public HashMap<String, Integer> getStatTable(){
		return statTable.getStatTable();
	}
	
	@Override
	public String toString() {
		String s = "";
		for(Gear gear : gearSet) {
			if(!(gear == null)) {
				s += gear.toString() + "\n";
			}
		}
		return s + "Average Gear Score: " + avgGearScore();
	}
}
