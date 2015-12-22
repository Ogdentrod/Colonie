package fr.kienanbachwa.colonie.graphics;

import java.util.ArrayList;
import java.util.List;

import fr.benoitsepe.colonie.elements.TypeElements;
import fr.benoitsepe.colonie.main.Gestion;
import fr.benoitsepe.colonie.ressources.TypeRessources;
import fr.kienanbachwa.colonie.jeu.Component;
import fr.kienanbachwa.colonie.jeu.Game;

public class Hud {
	
	List<Button> buttons = new ArrayList<Button>();
	List<TypeRessources> ressources = new ArrayList<TypeRessources>();
	
	int sizeX, sizeY;	//Taille du hud
	int x0,y0;		//position d'affichage du hud
	int x1;			//position d'affichage des ressources
	int sizeRes = 60;	//taille de l'affichage des ressources
	int sizeResIcon;
	Font font;
	
	public Hud(){
		try {
			font = new Font("res/minecraft_font.ttf", 6);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void init(){
		for(TypeElements e : TypeElements.values()){
			buttons.add(new Button(e.getTexture()));
		}
		
		for(TypeRessources res : TypeRessources.values()){
			ressources.add(res);
		}
	}
	
	public void update(){
		sizeX = 228;
		sizeY = 30;
		sizeResIcon = sizeY/ressources.size();
		
		x0 = (int)(-Game.xScroll +(Component.width/2 -sizeX/2));	//Milieu de l'�cran
		y0 =(int)(Component.height-sizeY-Game.yScroll);
		
		x1 = x0 +4+ TypeElements.values().length*((sizeX-sizeRes)/TypeElements.values().length);
		
		for(Button b:buttons){
			b.update(x0 +4+ (buttons.indexOf(b))*((sizeX-sizeRes)/TypeElements.values().length), y0+2, 16, 16);
		}
		
		try {
			font = new Font("res/stan0753.ttf", 8);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void render(){
		Renderer.renderQuad(x0, y0, sizeX, sizeY, new float[]{0,0,1,0.6f});
		
		for(Button b:buttons){
			b.getTexture().bind();
			Renderer.renderQuad(x0 +4+ (buttons.indexOf(b))*((sizeX-sizeRes)/TypeElements.values().length), y0+2, 16, 16, new float[]{1,1,1,1});
			b.getTexture().unbind();
			b.render();
		}
		
		//Renderer.renderQuad(x1, y0, sizeRes, sizeY, new float[]{1,1,1,1});
		
		for(TypeRessources res : ressources){
			res.getTexture().bind();
			Renderer.renderQuad(x1 + 1, y0 + ressources.indexOf(res)*sizeResIcon, sizeResIcon, sizeResIcon, new float[]{1,1,1,1});
			res.getTexture().unbind();
		}
		
		font.drawText(String.valueOf(Gestion.res.getWater()), x1+1+sizeResIcon, y0+sizeResIcon*0);
		font.drawText(String.valueOf(Gestion.res.getOxygen()), x1+1+sizeResIcon, y0+sizeResIcon*1);
		font.drawText(String.valueOf(Gestion.res.getIron()), x1+1+sizeResIcon, y0+sizeResIcon*2);
		font.drawText(String.valueOf(Gestion.res.getElec()), x1+1+sizeResIcon, y0+sizeResIcon*3);

	}
		

}