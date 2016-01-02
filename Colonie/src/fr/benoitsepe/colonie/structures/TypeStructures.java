package fr.benoitsepe.colonie.structures;

import fr.benoitsepe.colonie.ressources.Ressources;
import fr.kienanbachwa.colonie.graphics.Texture;

public enum TypeStructures {

	BATIMENT(new Ressources(0, 0, 10, 0)),
	SOL(new Ressources(0, 0, 10, 0)), 
	MUR(new Ressources(0, 0, 10, 0)), 
	PORTE(new Ressources(0, 0, 10, 0)),
	VIDE(new Ressources(0, 0, 0, 0));

	private Ressources res;
	private Texture texture;

	private TypeStructures(Ressources res) {
		this.res = res;
		texture = Texture.loadTexture(this.toString().toLowerCase());
	}
	
	public Ressources getRes() {
		return res;
	}

	public Texture getTexture() {
		return texture;
	}

}
