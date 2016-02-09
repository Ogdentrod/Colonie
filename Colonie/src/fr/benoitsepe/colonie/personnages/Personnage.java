package fr.benoitsepe.colonie.personnages;

public class Personnage {

	private int vie;
	
	
	public Personnage() {
		vie = 100;
	}
	
	
	public int infligeDegat(int degats) {
		vie -= degats;
		if (vie <= 0) {
			vie = 0;
			System.out.println("Personnage " + this.hashCode() + " est mort");
		}
		return vie;
	}
	
	public boolean isAlive() {
		if (vie == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	// A OVERRIDE
	public boolean utiliser() {
		System.out.println("Personnage non implémenté");
		return false;
	}


	public int getVie() {
		return vie;
	}
	
	
}
