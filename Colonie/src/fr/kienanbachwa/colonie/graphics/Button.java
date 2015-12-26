package fr.kienanbachwa.colonie.graphics;

import org.lwjgl.input.Mouse;

import fr.benoitsepe.colonie.elements.TypeElements;
import fr.kienanbachwa.colonie.jeu.Component;
import fr.kienanbachwa.colonie.jeu.Game;

public class Button extends Thing{

	boolean clicked = false;
	boolean hovered = false;
	int x,y,w,h;
	
	public Button(){
		
	}
	
	public void update(int x, int y, int w, int h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		
		if(Mouse.getX()/Component.scale>x && Mouse.getX()/Component.scale<x+w && Component.height-(Mouse.getY()/Component.scale)>y && Component.height-(Mouse.getY()/Component.scale)<y+h){
			hovered=true;
		}else{
			hovered=false;
		}
		
		if(Mouse.isButtonDown(0) && hovered){
			clicked=true;
		}else{
			clicked=false;
		}		
	}
	
	public void render(){
		if(hovered){
			Renderer.renderQuad(x,y,w,h, new float[]{1,1,1,0.5f});
		}
		
		if(hovered && clicked){
			Renderer.renderQuad(x,y,w,h, new float[]{1,0,0,0.5f});
		}
	}
}
