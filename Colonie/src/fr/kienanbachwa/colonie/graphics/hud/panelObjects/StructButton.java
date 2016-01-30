package fr.kienanbachwa.colonie.graphics.hud.panelObjects;

import fr.benoitsepe.colonie.structures.TypeStructures;
import fr.kienanbachwa.colonie.graphics.Texture;
import fr.kienanbachwa.colonie.graphics.hud.Hud;

public class StructButton extends Button{

	Texture texture;
	TypeStructures typeStructure;
	int x,y,w,h;
	
	public StructButton(TypeStructures e){
		super();
		typeStructure = e;
		this.texture=e.getTexture();
	}
	
	public void update(int x, int y, int w, int h){
		super.update(x, y, w, h);
		if(clicked){
			Hud.elementClicked=this.typeStructure;
		}
	}
	
	public void setTexture(Texture texture){
		this.texture=texture;
	}
	
	public Texture getTexture(){
		return texture;
	}
}
