package fr.kienanbachwa.colonie.graphics;

import java.util.ArrayList;
import java.util.List;

import fr.benoitsepe.colonie.elements.TypeElements;
import fr.kienanbachwa.colonie.jeu.Component;
import fr.kienanbachwa.colonie.jeu.Game;

public class Hud {
	
	List<TypeElements> elements = new ArrayList<TypeElements>();
	int sizeX, sizeY;
	int x0,y0;
	int x1;
	public Hud(){
		
	}
	
	public void init(){
		for(TypeElements e : TypeElements.values()){
			elements.add(e);
		}
		

	}
	
	public void update(){
		sizeX = 228;
		sizeY = 20;
		
		x0 = (int)(-Game.xScroll +(Component.width/2 -sizeX/2));
		y0 =(int)(Component.height-sizeY-Game.yScroll);
		
		x1 = x0 +4+ TypeElements.values().length*((sizeX-40)/TypeElements.values().length);
	}
	
	public void render(){
		Renderer.renderQuad(x0, y0, sizeX, sizeY, new float[]{0,0,1,0.6f});
		
		for(TypeElements e : elements){
			e.getTexture().bind();
			Renderer.renderQuad(x0 +4+ (elements.indexOf(e))*((sizeX-40)/TypeElements.values().length), y0+2, 16, 16, new float[]{1,1,1,1});
			e.getTexture().unbind();
		}
		
		Renderer.renderQuad(x1, y0, x1-sizeX, sizeY, new float[]{1,1,1,1});
		
	}

}