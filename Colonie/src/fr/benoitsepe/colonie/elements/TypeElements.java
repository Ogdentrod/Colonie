package fr.benoitsepe.colonie.elements;

import fr.benoitsepe.colonie.main.Ressources;
import fr.kienanbachwa.colonie.graphics.Texture;

public enum TypeElements {

	SOL(new Ressources(0, 0, 10, 0)), 
	MUR(new Ressources(5, 0, 10, 0)), 
	VIDE(new Ressources(0, 0, 0, 0));

	private Ressources res;
	private Texture texture;

	private TypeElements(Ressources res) {
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
