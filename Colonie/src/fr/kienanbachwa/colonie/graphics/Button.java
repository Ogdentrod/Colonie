package fr.kienanbachwa.colonie.graphics;

import org.lwjgl.input.Mouse;

import fr.kienanbachwa.colonie.jeu.Component;
import fr.kienanbachwa.colonie.jeu.Game;

public class Button {

	boolean clicked = false;
	Texture texture;
	int x,y,w,h;
	
	public Button(Texture tex){
		this.texture=tex;
	}
	
	public boolean update(int x, int y, int w, int h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		
		if(Mouse.isButtonDown(0) && Mouse.getX()/Component.scale -Game.xScroll>x && Mouse.getX()/Component.scale -Game.xScroll<x+w && Component.height-(Mouse.getY()/Component.scale)-Game.yScroll>y ){
			clicked=true;

		}else{
			clicked=false;
		}
		return clicked;
	}
	
	public void render(){
		if(  Mouse.getX()/Component.scale -Game.xScroll>x && Mouse.getX()/Component.scale -Game.xScroll<x+w && Component.height-(Mouse.getY()/Component.scale)-Game.yScroll>y ){
			Renderer.renderQuad(x,y,w,h, new float[]{1,1,1,0.25f});
		}
		
		if(clicked){
			Renderer.renderQuad(x,y,w,h, new float[]{1,0,0,0.25f});
		}
	}
	
	public void setTexture(Texture texture){
		this.texture=texture;
	}
	
	public Texture getTexture(){
		return texture;
	}
}
