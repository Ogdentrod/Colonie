package fr.benoitsepe.colonie.personnages;

import fr.benoitsepe.colonie.structures.Structure;

public class Ouvrier extends Personnage{
	
	private Structure occupation;
	
	public Ouvrier() {
		super();
	}
	
	public boolean utiliser() {
		if(occupation.construire()) {
			return true;
		} else {
			return false;
		}
	}


	public Structure getOccupation() {
		return occupation;
	}

	public void setOccupation(Structure occupation) {
		this.occupation = occupation;
	}
	
	
}
