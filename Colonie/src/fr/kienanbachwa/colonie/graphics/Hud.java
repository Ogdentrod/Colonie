package fr.kienanbachwa.colonie.graphics;

import java.util.ArrayList;
import java.util.List;

import fr.benoitsepe.colonie.elements.TypeElements;
import fr.kienanbachwa.colonie.jeu.Component;
import fr.kienanbachwa.colonie.jeu.Game;

public class Hud {
	
	List<Button> buttons = new ArrayList<Button>();
	int sizeX, sizeY;	//Taille du hud
	int x0,y0;		//position d'affichage du hud
	int x1;			//position d'affichage des ressources
	int sizeRes = 60;	//taille de l'affichage des ressources
	public Hud(){
		
	}
	
	public void init(){
		for(TypeElements e : TypeElements.values()){
			buttons.add(new Button(e.getTexture()));
		}
	}
	
	public void update(){
		sizeX = 228;
		sizeY = 20;
		
		x0 = (int)(-Game.xScroll +(Component.width/2 -sizeX/2));	//Milieu de l'écran
		y0 =(int)(Component.height-sizeY-Game.yScroll);
		
		x1 = x0 +4+ TypeElements.values().length*((sizeX-sizeRes)/TypeElements.values().length);
		
		for(Button b:buttons){
			b.update(x0 +4+ (buttons.indexOf(b))*((sizeX-sizeRes)/TypeElements.values().length), y0+2, 16, 16);
		}
	}
	
	public void render(){
		Renderer.renderQuad(x0, y0, sizeX, sizeY, new float[]{0,0,1,0.6f});

		
		Renderer.renderQuad(x1, y0, sizeRes, sizeY, new float[]{1,1,1,1});
		
		for(Button b:buttons){
			b.getTexture().bind();
			Renderer.renderQuad(x0 +4+ (buttons.indexOf(b))*((sizeX-sizeRes)/TypeElements.values().length), y0+2, 16, 16, new float[]{1,1,1,1});
			b.getTexture().unbind();
			b.render();
		}
	}

}