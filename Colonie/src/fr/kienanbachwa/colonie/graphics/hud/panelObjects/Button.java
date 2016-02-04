package fr.kienanbachwa.colonie.graphics.hud.panelObjects;


import org.lwjgl.input.Mouse;

import fr.kienanbachwa.colonie.graphics.Renderer;
import fr.kienanbachwa.colonie.graphics.Texture;
import fr.kienanbachwa.colonie.jeu.Component;
import fr.kienanbachwa.colonie.graphics.Color;


public class Button extends Thing{

	public boolean clicked = false;
	public boolean hovered = false;
	protected boolean isSelected;
	
	public Button(){
		super();
		this.texture=Texture.loadTexture("buttonText");
	}
	
	public void update(int x, int y, int w, int h){
		super.update(x, y, w, h);
		
		if(Mouse.getX()/Component.scale>x && Mouse.getX()/Component.scale<x+w && Component.height-(Mouse.getY()/Component.scale)>y && Component.height-(Mouse.getY()/Component.scale)<y+h){
			hovered=true;
		}else{
			hovered=false;
		}
		
		if(Mouse.isButtonDown(0) && hovered){
			this.clicked=true;
		}else{
			this.clicked=false;
		}
		
		if(clicked){
			this.select();
		}
	}
	
	public void render(){
		if(this.getTexture()!=null) this.getTexture().bind();
		Renderer.renderQuad(x, y, w, h, new float[]{1,1,1,1});
		if(this.getTexture()!=null) this.getTexture().unbind();
		
		if(hovered){
			Renderer.renderQuad(x,y,w,h, new float[]{0,0,0,0.2f});
			
			
		}
		
		if(hovered && clicked){
			Renderer.renderQuad(x,y,w,h, new float[]{0,0,0,0.4f});
		}
		
	}
	
	public boolean isClicked(){
		return clicked;
	}
	
	public boolean isHovered(){
		return hovered;
	}
	
	public void select(){
		isSelected=true;
	}
	
	public void deselect(){
		isSelected=false;

	}
	
	public boolean isSelected(){
		return isSelected;
	}
}
