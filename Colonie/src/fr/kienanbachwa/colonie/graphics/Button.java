package fr.kienanbachwa.colonie.graphics;

import org.lwjgl.input.Mouse;

import fr.benoitsepe.colonie.elements.TypeElements;
import fr.kienanbachwa.colonie.jeu.Component;
import fr.kienanbachwa.colonie.jeu.Game;

public class Button {

	boolean clicked = false;
	boolean hovered = false;
	int x,y,w,h;
	
	public Button(){
		
	}
	
	public boolean update(int x, int y, int w, int h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		
		if(Mouse.getX()/Component.scale -Game.xScroll>x && Mouse.getX()/Component.scale -Game.xScroll<x+w && Component.height-(Mouse.getY()/Component.scale)-Game.yScroll>y && Component.height-(Mouse.getY()/Component.scale)-Game.yScroll<y+h){
			hovered=true;
		}else{
			hovered=false;
		}
		
		if(Mouse.isButtonDown(0) && hovered){
			clicked=true;
		}else{
			clicked=false;
		}
				
		return clicked;
		
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
