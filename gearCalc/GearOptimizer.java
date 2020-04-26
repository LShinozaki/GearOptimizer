package gearCalc;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GearOptimizer {
	private Armory armory;
	private Character best;
	private ArrayList<Character> characterSet;
	
	public GearOptimizer(String file) {
		armory = new Armory();
		armory.getOptimizationList(file);
		characterSet = new ArrayList<>();
	}
	
	public void optimize(String optimizationString) {
		int[] indices = new int[6];
		
		String[] parts = optimizationString.split(",");
		Character character = new Character(parts[0]);
		
		ArrayList<String> setList = new ArrayList<>();
		String [] sets = parts[1].split(";");
		for(int i = 0; i < sets.length; i++) {
			setList.add(sets[i]);
		}
		
		ArrayList<String> conditions = new ArrayList<>();
		String[] conditionParts = parts[2].split(";");
		for(int i = 0; i < conditionParts.length; i++) {
			conditions.add(conditionParts[i]);
		}
		
		HashMap<String, Double> weightedStats = new HashMap<>();
		String[] weights = parts[3].split(";");
		for(int i = 0; i < weights.length; i+= 2) {
			weightedStats.put(weights[i], Double.valueOf(weights[i+1]));
		}
		
		GearSet gs = new GearSet();
		
		for(int i = 0; i < armory.weaponList.size(); i++) {
			if(!(setList.contains(armory.weaponList.get(i).getSet()))) {
				continue;
			}
			gs.equip(armory.weaponList.get(i));
			for(int j = 0; j < armory.helmetList.size(); j++) {
				if(!(setList.contains(armory.helmetList.get(j).getSet()))) {
					continue;
				}
				gs.equip(armory.helmetList.get(j));
				for(int k = 0; k < armory.chestList.size(); k++) {
					if(!(setList.contains(armory.chestList.get(k).getSet()))) {
						continue;
					}
					gs.equip(armory.chestList.get(k));
					for(int l = 0; l < armory.necklaceList.size(); l++) {
						if(!(setList.contains(armory.necklaceList.get(l).getSet()))) {
							continue;
						}
						gs.equip(armory.necklaceList.get(l));
						for(int m = 0; m < armory.ringList.size(); m++) {
							if(!(setList.contains(armory.ringList.get(m).getSet()))) {
								continue;
							}
							gs.equip(armory.ringList.get(m));
							for(int n = 0; n < armory.bootsList.size(); n++) {
								if(!(setList.contains(armory.bootsList.get(n).getSet()))) {
									continue;
								}
								gs.equip(armory.bootsList.get(n));
								
								character.unequip();
								character.equip(gs);
								if(parseBoolean(character, conditions)) {
									if(isBestCharacter(character, weightedStats)) {
										indices = new int[] {i, j, k, l ,m, n};
									}
								}
							}
						}
					}
				}
			}
		}
		gs.equip(armory.weaponList.get(indices[0]));
		gs.equip(armory.helmetList.get(indices[1]));
		gs.equip(armory.chestList.get(indices[2]));
		gs.equip(armory.necklaceList.get(indices[3]));
		gs.equip(armory.ringList.get(indices[4]));
		gs.equip(armory.bootsList.get(indices[5]));
		System.out.println(gs);
		if(best == null) {
			return;
		} else {
			System.out.println(best.getGearSet());
			characterSet.add(best);
			removeGear(indices);
			best = null;
		} 
	}
	
	public boolean parseBoolean(Character character, ArrayList<String> conditions) {
		for(String condition : conditions) {
			String[] parts = condition.split(" ");
			if(parts[0].equals("has")) {
				 if(!(character.hasSet(parts[1]))) {
					 return false;
				 }
			} else {
				switch(parts[1]) {
				case "<":
					if(!(character.get(parts[0]) < Integer.valueOf(parts[2]))) {
						return false;
					}
				break;
				case ">":
					if(!(character.get(parts[0]) > Integer.valueOf(parts[2]))) {
						return false;
					}
				break;
				}
			}
		}
		return true;
	}
	
	public boolean isBestCharacter(Character character, HashMap<String, Double> weightedStats) {
		if(best == null) {
			best = new Character(character);
			System.out.println(best.getGearSet());
			return true;
		}
		double weight = 0;
		double sum = 0;
		for(String stat : weightedStats.keySet()) {
			weight += ((double) character.get(stat) / best.get(stat)) * weightedStats.get(stat);
			sum += weightedStats.get(stat);
		}
		System.out.println("weight: " + weight + " / sum: " + sum);
		if(weight > sum) {
			best = new Character(character);
			System.out.println(best.getGearSet());
			return true;
		}
		return false;
	}
	
	public void removeGear(int[] indices) {
			armory.weaponList.remove(indices[0]);
			armory.helmetList.remove(indices[1]);
			armory.chestList.remove(indices[2]);
			armory.necklaceList.remove(indices[3]);
			armory.ringList.remove(indices[4]);
			armory.bootsList.remove(indices[5]);
	}
	
	public void printToFile() throws FileNotFoundException {
		try (PrintStream out = new PrintStream(new FileOutputStream("C:\\Users\\Lsmit\\eclipse-workspace\\gearCalc\\src\\Optimized.txt"))) {
		    for(Character character : characterSet) {
		    	out.println(character);
		    }
		    out.close();
		}
	}
	
	public void start() throws FileNotFoundException {
		for (String string : armory.optimizationList) {
			optimize(string);
		}
		printToFile();
	}
}