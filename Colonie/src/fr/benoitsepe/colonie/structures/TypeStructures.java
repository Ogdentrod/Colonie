package fr.benoitsepe.colonie.structures;

import fr.benoitsepe.colonie.ressources.Ressources;
import fr.kienanbachwa.colonie.graphics.Texture;

// public Ressources(int water, int oxygen, int iron, int elec)
public enum TypeStructures {

	EOLIENNE(new Ressources(0, 0, 20, 0)),
	PANNEAUSOLAIRE(new Ressources(0, 0, 20, 0)),
	USINEOXYGENE(new Ressources(0, 10, 20, 0)),
	REFECTOIRE(new Ressources(0, 10, 20, 0)),
	SAS(new Ressources(0, 20, 20, 0));
	
	private Ressources res;
	private Texture texture;
	
	private TypeStructures(Ressources res) {
		this.res = res;
		texture = Texture.loadTexture(this.toString().toLowerCase());
	}
	
	public Ressources getRes() {
		return res;
	}
	
	public Texture getTexture(){
		return texture;
	}
	
	
	
}
