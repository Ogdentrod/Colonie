package fr.benoitsepe.colonie.main;

public class Ressources {

	private int water, oxygen, wood, iron, iron_ore, elec;
	
	
	public Ressources() {
		water = 0;
		oxygen = 0;
		wood = 10;
		iron = 10;
		iron_ore = 0;
		elec = 0;
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
