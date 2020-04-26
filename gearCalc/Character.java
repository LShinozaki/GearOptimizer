package gearCalc;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Character {
	private String name;
	private StatTable baseStatTable;
	private StatTable statTable;
	private GearSet gearSet;
	
	public Character(String name) {
		baseStatTable = new StatTable(getBaseStats(name));
		statTable = new StatTable(this.baseStatTable.statTable);
		this.name = name;
		gearSet = new GearSet();
	}
	
	public Character(Character character) {
		this.statTable = character.statTable;
		this.name = character.name;
		this.baseStatTable = character.baseStatTable;
		this.gearSet = new GearSet(character.gearSet);
	}
	
	public void equip(GearSet gearSet) {
		this.gearSet = gearSet;
		gearSet.calcStats();
		finalCalc();
		statTable.update(gearSet.getStatTable());
		if(get("CCh") > 100) {
			statTable.replace("CCh", 100);
		}
	}
	
	public boolean hasGearSet() {
		return !(gearSet == null);
	}
	
	public GearSet getGearSet() {
		return gearSet;
	}
	
	public void unequip() {
		gearSet = new GearSet();
		statTable = new StatTable(baseStatTable.statTable);
	}
	
	public HashMap<String,Integer> getBaseStats(String name){
		HashMap<String,Integer> baseStats = new HashMap<>();
		String[] stats = new String[] {"Atk", "HP", "Spd", "Def", "CCh", "CDmg", "Dual", "Eff", "Res"};
        try(Scanner reader = new Scanner(Paths.get("C:\\Users\\Lsmit\\eclipse-workspace\\gearCalc\\src\\E7 Gear Calculator - BaseStats.csv"))){
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                String[] split = line.split(",");
                if(split[0].equals(name)) {
                	int i = 0;
                	while (i < stats.length) {
                    	baseStats.put(stats[i], Integer.valueOf(split[i+1]));
                    	i++;
                	}
                }
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return baseStats;
	}
	
	public void finalCalc() {
		statTable.replace("Atk", (int) ((1 + (double) gearSet.get("Atk%")/100) * statTable.get("Atk")));
		statTable.replace("Def", (int) ((1 + (double) gearSet.get("Def%")/100) * statTable.get("Def")));
		statTable.replace("HP", (int) ((1 + (double) gearSet.get("HP%")/100) * statTable.get("HP")));
		if(hasSet("Spd")) {
			statTable.replace("Spd", (int) (statTable.get("Spd")*1.25));
		}
	}
	
	public boolean hasSet(String set) {
		return gearSet.getSetList().contains(set);
	}
	
	public int eHP() {
		return (int) (statTable.get("HP") * (((double) statTable.get("Def") / 300) + 1));
	}
	
	public int damage() {
		return (int) ((((double)statTable.get("Atk%")/100 + 1)*(1 + ((double)statTable.get("CCh")/100)*(((double)statTable.get("CDmg")/100)-1)))*100); 
	}
	
	public int dps() {
		return (int) (damage() * ((double) statTable.get("Spd") / 100));
	}
	
	public int get(String stat) {
		if(stat.equals("eHP")) {
			return eHP();
		}
		if(stat.equals("damage")) {
			return damage();
		}
		if(stat.equals("dps")) {
			return dps();
		}
		return statTable.get(stat);
	}
	
	public StatTable getStatTable() {
		return statTable;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name + "\n------\nAtk: " + 
				statTable.get("Atk") + " | CCh/CDmg: " + statTable.get("CCh") + "/" + statTable.get("CDmg") + " | Damage: " + damage() + " | DPS: " + dps() + 
				"\nHP/Def: " + statTable.get("HP") + "/" + statTable.get("Def") + " | EffHP: " + eHP() + " | Eff/Res " + statTable.get("Eff") + "/" + statTable.get("Res") + 
				" | Speed: " + statTable.get("Spd") + "\n------\n" + gearSet;
		
		
	}
}
