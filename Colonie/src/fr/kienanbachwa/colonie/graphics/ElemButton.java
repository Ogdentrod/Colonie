package fr.kienanbachwa.colonie.graphics;

import fr.benoitsepe.colonie.elements.TypeElements;

public class ElemButton extends Button{

	boolean clicked = false;
	boolean hovered = false;
	Texture texture;
	TypeElements typeElement;
	int x,y,w,h;
	
	public ElemButton(TypeElements e){
		typeElement = e;
		this.texture=e.getTexture();
	}
	
	public boolean update(int x, int y, int w, int h){
		super.update(x, y, w, h);
		if(clicked)	Hud.elementClicked=this.typeElement;
		
		return clicked;	
	}
	
	public void setTexture(Texture texture){
		this.texture=texture;
	}
	
	public Texture getTexture(){
		return texture;
	}
}
