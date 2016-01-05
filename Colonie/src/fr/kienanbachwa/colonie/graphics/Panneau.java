package fr.kienanbachwa.colonie.graphics;

import java.util.ArrayList;

import fr.kienanbachwa.colonie.graphics.things.OngletButton;
import fr.kienanbachwa.colonie.graphics.things.Thing;
import fr.kienanbachwa.colonie.jeu.Component;

public class Panneau {
	
	public int x,y,w,h;			//Position x, y, largeur et hauteur
	private int gridX=5, gridY=5;	//Colonnes(x) et lignes(y)
	boolean grid = false;
	ArrayList<Thing> array = new ArrayList<Thing>();	//Liste contenant tous les objets du panneau
	Texture texture;
	int type=1;	//Type 1: Les �lements seront centr�s au milieu du panneau en fonction de sa hauteur et de sa largeur
	private int index;
				//Type 2: Les �lements prendront toute la place disponible
				//Type 3: Les �l�ments sont dispos�s horizontalement
	
	public Panneau(int x, int y, int w, int h, int type){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.type=type;
		
		this.texture=Texture.loadTexture("hudText");
	}
	
	public Panneau(int x, int y, int w, int h, int gridX, int gridY){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.gridX=gridX;
		this.gridY=gridY;
		this.grid=true;
		
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
		
		if(type==1){
			for(Thing b : array){
				b.update(x + (w/array.size()/2)*(array.indexOf(b)*2+1)-(int)(24/Component.scale), y+(h/2)-(int)(24/Component.scale), (int)(48/Component.scale), (int)(48/Component.scale));
			}
		}
		if(type==2){
			for(Thing b : array){
				b.update(x + (w/array.size())*array.indexOf(b), y, w/array.size(), h);
				
				if(b.getClass() == OngletButton.class){
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
		
		if(type==3){
			for(Thing b : array){
				b.update(x, y + array.indexOf(b)*(h/array.size()), w, h/array.size());
			}
		}
		
		if(grid){	
			for(Thing b : array){
				b.update(x + array.indexOf(b)*(w/gridX), y, w/gridX, b.getW());
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
