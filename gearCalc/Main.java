package gearCalc;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		/*Weapon gear1 = new Weapon("W,88,15,Atk,515,17,,,,,16,8,15,,,,Spd,2,Alencia,92");
		Helmet gear2 = new Helmet("H,85,12,HP,1944,13,6,,,7,,6,,,,,Spd,4,Alencia,66");
		Chest gear3 = new Chest("C,85,12,HP,1944,13,6,,,7,,6,,,,,Spd,4,Alencia,66");
		Necklace gear4 = new Necklace("N,85,12,HP%,60,,6,,,7,,6,13,,,,Spd,4,Alencia,66");
		
		GearSet gs = new GearSet();
		gs.equip(gear1);
		gs.equip(gear2);
		gs.equip(gear3);
		gs.equip(gear4);
		
		Character aColi = new Character("AColi");
		System.out.println(aColi);
		aColi.equip(gs);
		System.out.println(aColi);
		aColi.unequip();
		System.out.println(aColi);
		
		GearSet gs2 = new GearSet();
		gs2.equip(gear1);
		gs2.equip(gear2);
		gs2.equip(gear3);
		gs2.equip(gear4);
		aColi.equip(gs2);
		System.out.println(aColi);*/
		
		GearOptimizer go = new GearOptimizer("C:\\Users\\Lsmit\\eclipse-workspace\\gearCalc\\src\\opt.csv");
		go.start();
		}
		
}
