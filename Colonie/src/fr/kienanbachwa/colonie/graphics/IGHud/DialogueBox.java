package fr.kienanbachwa.colonie.graphics.IGHud;

import java.util.ArrayList;

import fr.kienanbachwa.colonie.graphics.Renderer;
import fr.kienanbachwa.colonie.graphics.Texture;
import fr.kienanbachwa.colonie.graphics.hud.things.Thing;

public class DialogueBox {

	public int x,y,w,h;			//Position x, y, largeur et hauteur
	ArrayList<Thing> array = new ArrayList<Thing>();	//Liste contenant tous les objets du panneau
	Texture texture;
	
	public DialogueBox(int x, int y, int w, int h){
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
	
	public void update(int x, int y, int w, int h){
		this.x = x;		
		this.y = y;
		this.w=w;
		this.h=h;
	}
	
	public void render(){
		this.texture.bind();
		Renderer.renderQuad(x, y, w, h, new float[]{1,1,1,1});
		this.texture.unbind();
		
		for(Thing b : array){
			b.render();
		}
	}
	
	public void add(Thing o){
		array.add(o);		
	}
}
