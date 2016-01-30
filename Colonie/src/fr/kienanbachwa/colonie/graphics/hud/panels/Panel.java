package fr.kienanbachwa.colonie.graphics.hud.panels;

import java.util.ArrayList;

import fr.kienanbachwa.colonie.graphics.Color;
import fr.kienanbachwa.colonie.graphics.Renderer;
import fr.kienanbachwa.colonie.graphics.Texture;
import fr.kienanbachwa.colonie.graphics.hud.panelObjects.OngletButton;
import fr.kienanbachwa.colonie.graphics.hud.panelObjects.Thing;
import fr.kienanbachwa.colonie.jeu.Component;

public class Panel {
	
	public int x,y,w,h;			//Position x, y, largeur et hauteur
	ArrayList<Thing> array = new ArrayList<Thing>();	//Liste contenant tous les objets du panneau
	Texture texture;
	
	public Panel(int x, int y, int w, int h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		
		this.texture=Texture.loadTexture("hudText");
	}
	
	public void init(){
		for(Thing b : array){
			b.init();
		}
	}
	
	public void render(){
		this.texture.bind();
		Renderer.renderQuad(x, y, w, h, new float[]{1,1,1,1});
		this.texture.unbind();
		
		Renderer.renderQuad(x, y, w, 1, Color.BLACK);
		Renderer.renderQuad(x, y, 1, h, Color.BLACK);
		Renderer.renderQuad(x+w, y, w, 1, Color.BLACK);
		Renderer.renderQuad(x, y+h, 1, h, Color.BLACK);

		
		for(Thing b : array){
			b.render();
		}
	}
	
	public void add(Thing o){
		array.add(o);
	}
}
