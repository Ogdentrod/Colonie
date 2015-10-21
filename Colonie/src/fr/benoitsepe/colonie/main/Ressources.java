package fr.benoitsepe.colonie.main;

/**
 * @author Benoît
 *
 * Class ressources
 * 
 */
public class Ressources {

	/**
	 * Liste des ressources (à compléter)
	 */
	private int water, oxygen, iron, iron_ore, elec, wood;
	
	
	public Ressources() {
		water = 0;
		oxygen = 0;
		wood = 10;
		iron = 10;
		iron_ore = 0;
		elec = 0;
	}
	
	public Ressources(int water, int oxygen, int iron, int wood) {
		this.water = water;
		this.oxygen = oxygen;
		this.iron = iron;
		this.wood = wood;
	}
	
	
	public static void utiliserRessources(Ressources main, Ressources cout) {
		// FAIRE LES VERIFS D'ABORD
		
		main.setWater(main.getWater() - cout.getWater());
		main.setOxygen(main.getOxygen() - cout.getOxygen());
		main.setIron(main.getIron() - cout.getIron());
		main.setWood(main.getWood() - cout.getWood());
	}
	
	
	public int getElec() {
		return elec;
	}

	public void setElec(int elec) {
		this.elec = elec;
	}

	public int getWater() {
		return water;
	}

	public void setWater(int water) {
		this.water = water;
	}

	public int getOxygen() {
		return oxygen;
	}

	public void setOxygen(int oxygen) {
		this.oxygen = oxygen;
	}

	public int getWood() {
		return wood;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}

	public int getIron() {
		return iron;
	}

	public void setIron(int iron) {
		this.iron = iron;
	}

	public int getIron_ore() {
		return iron_ore;
	}

	public void setIron_ore(int iron_ore) {
		this.iron_ore = iron_ore;
	}
	
}
