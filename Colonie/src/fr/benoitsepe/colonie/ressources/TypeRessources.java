package fr.benoitsepe.colonie.ressources;

import fr.kienanbachwa.colonie.graphics.Texture;

public enum TypeRessources {
	
	WATER(),
	OXYGEN(),
	IRON(),
	ELECTRICITY();
	
	private Texture texture;
	
	private TypeRessources() {
		texture = Texture.loadTexture(this.toString().toLowerCase());
	}
	
	public Texture getTexture(){
		return texture;
	}
}
