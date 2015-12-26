package fr.kienanbachwa.colonie.graphics;

import java.util.ArrayList;

import fr.kienanbachwa.colonie.jeu.Game;

public class Panneau {
	
	private static float x,y,w,h;			//Position x, y, largeur et hauteur
	private static int gridX, gridY;	//Colonnes(x) et lignes(y)
	ArrayList<Button> array = new ArrayList<Button>();	//Liste contenant tous les objets du panneau
	
	public Panneau(int x, int y, int w, int h){
		Panneau.x=x;
		Panneau.y=y;
		Panneau.w=w;
		Panneau.h=h;
	}
	
	public void init(){
		
	}
	
	public void update(){
		x=x+Game.xScroll;
		y=y+Game.yScroll;
	}
	
	public void render(){
		for(Button o : array){
			o.render();				
		}
	}
	
	public void add(Button o){
		if(array.get(gridX*gridY)!=null){
			System.err.println("Le panneau ne peut pas contenir plus d'objets");
		}else{
			array.add(o);
		}
	}

}
