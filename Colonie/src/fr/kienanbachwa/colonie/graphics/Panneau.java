package fr.kienanbachwa.colonie.graphics;

import java.util.ArrayList;

import fr.kienanbachwa.colonie.graphics.things.OngletButton;
import fr.kienanbachwa.colonie.graphics.things.Thing;
import fr.kienanbachwa.colonie.jeu.Component;

public class Panneau {
	
	public int x,y,w,h;			//Position x, y, largeur et hauteur
	private int gridX=5, gridY=5;	//Colonnes(x) et lignes(y)
	ArrayList<Thing> array = new ArrayList<Thing>();	//Liste contenant tous les objets du panneau
	Texture texture;
	int type;	//Type 1: Les élements seront centrés au milieu du panneau en fonction de sa hauteur et de sa largeur
				//Type 2: Les élements prendront toute la place disponible
	public Panneau(int x, int y, int w, int h, int type){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.type=type;
		
		this.texture=Texture.loadTexture("hudText");
	}
	
	public void init(){
	}
	
	public void update(int x, int y, int w, int h){
		this.x = x;		
		this.y = y;
		this.w=w;
		this.h=h;
		
		if(type==1){
			for(Thing b : array){
				b.update(x + (w/array.size()/2)*(array.indexOf(b)*2+1)-(int)(24/Component.scale), y+(h/2)-(int)(24/Component.scale), (int)(48/Component.scale), (int)(48/Component.scale));
			}
		}
		if(type==2){
			for(Thing b : array){
				b.update(x + (w/array.size())*array.indexOf(b), y, w/array.size(), h);
				
				if(b.getClass() == OngletButton.class){		//
					if(((OngletButton)b).isSelected()){
						for(Thing c : array){
							if(((OngletButton)b != ((OngletButton)c))){
								((OngletButton)c).deselect();
							}
						}
					}
				}
				
			}
		}
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
		if(array.size()>=gridX*gridY){
			System.err.println("Le panneau ne peut pas contenir plus d'objets");
		}else{
			array.add(o);
		}
	}

}
