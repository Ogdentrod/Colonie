package fr.benoitsepe.colonie.ressources;

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
	private int water;
	private int oxygen;
	private int iron;
	private int iron_ore;
	private int electricity;
	
	
	public Ressources() {
		water = 0;
		oxygen = 0;
		iron = 50;
		iron_ore = 0;
		electricity = 0;
	}
	
	public Ressources(int water, int oxygen, int iron, int elec) {
		this.water = water;
		this.oxygen = oxygen;
		this.iron = iron;
		this.electricity = elec;
	}
	
	
	public static void utiliserRessources(Ressources main, Ressources cout) {
		// FAIRE LES VERIFS D'ABORD
		
		main.setWater(main.getWater() - cout.getWater());
		main.setOxygen(main.getOxygen() - cout.getOxygen());
		main.setIron(main.getIron() - cout.getIron());
		main.setElec(main.getElec() - cout.getElec());
	}
	
	
	
	/**
	 * @param r cout de la structure
	 * @return true si le joueur a assez de ressources, false sinon
	 */
	public boolean canBuy(Ressources r) {
		if (this.water >= r.getWater() && this.oxygen >= r.getOxygen() && this.iron >= r.getIron() && this.iron_ore >= r.getIron_ore() && this.electricity >= r.getElec()) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	public int getElec() {
		return electricity;
	}

	public void setElec(int elec) {
		this.electricity = elec;
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
	
	public int getQuantity(TypeRessources typeRes){
		int quantity=0;
		if(typeRes==TypeRessources.ELECTRICITY)
			quantity=electricity;
		if(typeRes==TypeRessources.IRON)
			quantity=iron;
		if(typeRes==TypeRessources.OXYGEN)
			quantity=oxygen;
		if(typeRes==TypeRessources.WATER)
			quantity=water;
		return quantity;
				
	}
	
}
