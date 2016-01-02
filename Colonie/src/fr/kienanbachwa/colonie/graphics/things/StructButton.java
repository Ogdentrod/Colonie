package fr.kienanbachwa.colonie.graphics.things;

import fr.benoitsepe.colonie.structures.TypeStructures;
import fr.benoitsepe.colonie.zone.TypeZones;
import fr.kienanbachwa.colonie.graphics.Hud;
import fr.kienanbachwa.colonie.graphics.Texture;

public class StructButton extends Button{

	Texture texture;
	TypeZones typeStructure;
	int x,y,w,h;
	
	public StructButton(TypeZones e){
		super();
		typeStructure = e;
		this.texture=e.getTexture();
	}
	
	public void update(int x, int y, int w, int h){
		super.update(x, y, w, h);
		if(clicked){
			//Hud.elementClicked=this.typeElement;
		}
	}
	
	public void setTexture(Texture texture){
		this.texture=texture;
	}
	
	public Texture getTexture(){
		return texture;
	}
}
