package fr.kienanbachwa.colonie.graphics;

import java.util.ArrayList;

import fr.kienanbachwa.colonie.jeu.Component;

public class Panneau {
	
	private int x,y,w,h;			//Position x, y, largeur et hauteur
	private int gridX=5, gridY=5;	//Colonnes(x) et lignes(y)
	ArrayList<Thing> array = new ArrayList<Thing>();	//Liste contenant tous les objets du panneau
	Texture texture;
	
	public Panneau(int x, int y, int w, int h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		
		this.texture=Texture.loadTexture("sol");
	}
	
	public void init(){
	}
	
	public void update(int x, int y, int w, int h){
		this.x = x;		
		this.y = y;
		this.w=w;
		this.h=h;
		
		for(Thing b : array){
			b.update();
		}
		
	}
	
	public void render(){
		this.texture.bind();
		Renderer.renderQuad(x, y, w, h, new float[]{1,1,1,1});
		this.texture.unbind();
		
		for(Thing b : array){
			if(b.getTexture()!=null) b.getTexture().bind();
			Renderer.renderQuad(x + (array.indexOf(b))*(w/array.size()), y, 16, 16, new float[]{1,1,1,1});
			if(b.getTexture()!=null) b.getTexture().unbind();
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
