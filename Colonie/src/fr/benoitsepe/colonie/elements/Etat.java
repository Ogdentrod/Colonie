package fr.benoitsepe.colonie.elements;

import fr.benoitsepe.colonie.ressources.Ressources;
import fr.kienanbachwa.colonie.graphics.Texture;

public enum Etat {
	QUEUED,
	CONSTRUCTION,
	OPERATIONNAL;
	
	private Texture texture;

	private Etat() {
		if (this.toString() != "OPERATIONNAL") {
			texture = Texture.loadTexture(this.toString().toLowerCase());
		} else {
			texture = null;
		}
	}
	

	public Texture getTexture() {
		return texture;
	}
	
	
}
