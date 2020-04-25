package gearCalc;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Armory {
	public ArrayList<Weapon> weaponList;
	public ArrayList<Helmet> helmetList;
	public ArrayList<Chest> chestList;
	public ArrayList<Necklace> necklaceList;
	public ArrayList<Ring> ringList;
	public ArrayList<Boots> bootsList;
	public ArrayList<String> optimizationList;
	
	public Armory() {
		weaponList = getWeapon("C:\\Users\\Lsmit\\eclipse-workspace\\gearCalc\\src\\Weapons.csv");
		helmetList = getHelmet("C:\\Users\\Lsmit\\eclipse-workspace\\gearCalc\\src\\Helmets.csv");
		chestList = getChest("C:\\Users\\Lsmit\\eclipse-workspace\\gearCalc\\src\\Chests.csv");
		necklaceList = getNecklace("C:\\Users\\Lsmit\\eclipse-workspace\\gearCalc\\src\\Necklaces.csv");
		ringList = getRing("C:\\Users\\Lsmit\\eclipse-workspace\\gearCalc\\src\\Rings.csv");
		bootsList = getBoots("C:\\Users\\Lsmit\\eclipse-workspace\\gearCalc\\src\\Boots.csv");
		optimizationList = new ArrayList<>();
	}
	
	public ArrayList<Weapon> getWeapon(String file){
		ArrayList<Weapon> weaponList = new ArrayList<>();
		 try(Scanner reader = new Scanner(Paths.get(file))){
	            while(reader.hasNextLine()){
	                String line = reader.nextLine();
	                Weapon weapon = new Weapon(line);
	                weaponList.add(weapon);
	            }
	        } catch (Exception e){
	            System.out.println("Error: " + e.getMessage());
	        }
		 return weaponList;
	}
	
	public ArrayList<Helmet> getHelmet(String file){
		ArrayList<Helmet> helmetList = new ArrayList<>();
		 try(Scanner reader = new Scanner(Paths.get(file))){
	            while(reader.hasNextLine()){
	                String line = reader.nextLine();
	                Helmet helmet = new Helmet(line);
	                helmetList.add(helmet);
	            }
	        } catch (Exception e){
	            System.out.println("Error: " + e.getMessage());
	        }
		 return helmetList;
	}
	
	public ArrayList<Chest> getChest(String file){
		ArrayList<Chest> chestList = new ArrayList<>();
		 try(Scanner reader = new Scanner(Paths.get(file))){
	            while(reader.hasNextLine()){
	                String line = reader.nextLine();
	                Chest chest = new Chest(line);
	                chestList.add(chest);
	            }
	        } catch (Exception e){
	            System.out.println("Error: " + e.getMessage());
	        }
		 return chestList;
	}
	
	public ArrayList<Necklace> getNecklace(String file){
		ArrayList<Necklace> necklaceList = new ArrayList<>();
		 try(Scanner reader = new Scanner(Paths.get(file))){
	            while(reader.hasNextLine()){
	                String line = reader.nextLine();
	                Necklace necklace = new Necklace(line);
	                necklaceList.add(necklace);
	            }
	        } catch (Exception e){
	            System.out.println("Error: " + e.getMessage());
	        }
		 return necklaceList;
	}
	
	public ArrayList<Ring> getRing(String file){
		ArrayList<Ring> ringList = new ArrayList<>();
		 try(Scanner reader = new Scanner(Paths.get(file))){
	            while(reader.hasNextLine()){
	                String line = reader.nextLine();
	                Ring ring = new Ring(line);
	                ringList.add(ring);
	            }
	        } catch (Exception e){
	            System.out.println("Error: " + e.getMessage());
	        }
		 return ringList;
	}
	
	public ArrayList<Boots> getBoots(String file){
		ArrayList<Boots> bootsList = new ArrayList<>();
		 try(Scanner reader = new Scanner(Paths.get(file))){
	            while(reader.hasNextLine()){
	                String line = reader.nextLine();
	                Boots boots = new Boots(line);
	                bootsList.add(boots);
	            }
	        } catch (Exception e){
	            System.out.println("Error: " + e.getMessage());
	        }
		 return bootsList;
	}
	
	public void getOptimizationList(String file) {
		 try(Scanner reader = new Scanner(Paths.get(file))){
	            while(reader.hasNextLine()){
	                String line = reader.nextLine();
	                optimizationList.add(line);
	            }
	        } catch (Exception e){
	            System.out.println("Error: " + e.getMessage());
	        }
	}
}
