package fr.kienanbachwa.colonie.graphics.hud.panelObjects;

import org.lwjgl.input.Mouse;

import fr.kienanbachwa.colonie.graphics.Renderer;
import fr.kienanbachwa.colonie.jeu.Component;

public class Button extends Thing{

	boolean clicked = false;
	boolean hovered = false;
	
	public Button(){
		super();
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
	}
	
	public void render(){
		if(this.getTexture()!=null) this.getTexture().bind();
		Renderer.renderQuad(x, y, w, h, new float[]{1,1,1,1});
		if(this.getTexture()!=null) this.getTexture().unbind();
		
		if(hovered){
			Renderer.renderQuad(x,y,w,h, new float[]{1,1,1,0.5f});
		}
		
		if(hovered && clicked){
			Renderer.renderQuad(x,y,w,h, new float[]{1,0,0,0.5f});
		}
	}
	
	public boolean isClicked(){
		return clicked;
	}
	
	public boolean isHovered(){
		return hovered;
	}
}
