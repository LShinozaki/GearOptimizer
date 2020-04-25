package gearCalc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StatTable {
	public HashMap<String, Integer> statTable;
	
	public StatTable() {
		statTable = new HashMap<>();
		String[] stats = new String[] {"HP%", "Def%", "Res", "Atk%", "CCh",	"CDmg", "Spd", "Eff", "HP", "Atk", "Def", "Dual"};
		for(int i = 0; i < stats.length; i++) {
			statTable.put(stats[i], 0);
		}
	}
	
	public StatTable(HashMap<String, Integer> statTable) {
		this.statTable = new HashMap<>();
		String[] stats = new String[] {"HP%", "Def%", "Res", "Atk%", "CCh",	"CDmg", "Spd", "Eff", "HP", "Atk", "Def", "Dual"};
		for(int i = 0; i < stats.length; i++) {
			this.statTable.put(stats[i], 0);
		}
		update(statTable);
	}
	
	public void update(HashMap<String, Integer> statTable){
		for(String key : statTable.keySet()) {
			this.statTable.replace(key, this.statTable.get(key) + statTable.get(key));
		}
	}
	
	public void update(String key, int value) {
		statTable.replace(key, statTable.get(key) + value);
	}
	
	public int get(String key) {
		return statTable.get(key);
	}
	
	public void replace(String key, int value) {
		statTable.replace(key, value);
	}
	
	public HashMap<String, Integer> getStatTable(){
		return statTable;
	}
	
	@Override
	public String toString() {
		String stats = "";
		for(String key : statTable.keySet()) {
			stats += key + ": " + statTable.get(key) + " | ";
		}
		return stats;
	}
}
