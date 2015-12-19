package fr.benoitsepe.colonie.main;

import fr.kienanbachwa.colonie.graphics.Texture;

public enum TypeStructures {
	RIEN(new Ressources(0, 0, 0, 0)),
	EOLIENNE(new Ressources(0, 0, 20, 0)),
	PANNEAU_SOLAIRE(new Ressources(0, 0, 20, 0)),
	USINE_OXYGENE(new Ressources(0, 10, 20, 0)),
	REFECTOIRE(new Ressources(0, 10, 20, 0)),
	SAS(new Ressources(0, 20, 20, 0)),
	CONNEXION(new Ressources(0, 0, 4, 0)),
	COULOIR(new Ressources(0, 10, 10, 0));
	
	private Ressources res;
	
	private TypeStructures(Ressources res) {
		this.res = res;
	}
	
	public Ressources getRes() {
		return res;
	}
	
	
	
}
