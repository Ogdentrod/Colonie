package fr.kienanbachwa.colonie.graphics;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import fr.benoitsepe.colonie.main.Gestion;
import fr.benoitsepe.colonie.ressources.TypeRessources;
import fr.benoitsepe.colonie.structures.TypeStructures;
import fr.kienanbachwa.colonie.graphics.things.StructButton;
import fr.kienanbachwa.colonie.jeu.Component;
import fr.kienanbachwa.colonie.jeu.Game;

public class Hud2 {
	
	List<StructButton> buttons = new ArrayList<StructButton>();
	List<TypeRessources> ressources = new ArrayList<TypeRessources>();
	
	private int sizeX, sizeY;	//Taille du hud
	private int x0,y0;		//position d'affichage du hud
	private int xRes;			//position d'affichage des ressources
	private int sizeRes = 60;	//taille de l'affichage des ressources
	private int sizeResIcon;
	private Font font;
	private int y1;
	private int sizeTab = 10;		//Taille des onglets
	public static boolean mouseOnHud;
	public static TypeStructures elementClicked = TypeStructures.BATIMENT;
	
	
	public Hud2(){
		try {
			font = new Font("res/minecraft_font.ttf", 6);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sizeX = 228;
		sizeY = 60;
		sizeResIcon = 8;
		
	}
	
	public void init(){
		for(TypeStructures e : TypeStructures.values()){
			buttons.add(new StructButton(e));				//Boutons d'élements
		}
		
		for(TypeRessources res : TypeRessources.values()){
			ressources.add(res);
		}
		
		try {												//THIS WAS TAKING MEMORY
			font = new Font("res/stan0753.ttf", 8);
		} catch (Exception e) {
			System.err.println("Police non trouvée");
			e.printStackTrace();
		}
		
	}
	
	public void update(){
		//Position en haut à gauche du hud
		x0 = (int)(-Game.xScroll +(Component.width/2 -sizeX/2));		
		y0 =(int)(Component.height-sizeY-Game.yScroll);					
		
		//Position d'affichage des autres trucs
		y1=y0+sizeTab ;
		
		//Position d'affichage des ressources
		xRes = x0 +4+ TypeStructures.values().length*((sizeX-sizeRes)/TypeStructures.values().length);
		
		for(StructButton b:buttons){
			b.update(x0 +4+ (buttons.indexOf(b))*((sizeX-sizeRes)/TypeStructures.values().length), y1+2, 16, 16);
		}
		
		if(Mouse.getX() > x0*Component.scale+Game.xScroll && Mouse.getX()<(x0+sizeX)*Component.scale+Game.xScroll && Mouse.getY()<(y0+sizeY)*Component.scale+Game.yScroll){
			mouseOnHud=true;
		}else{
			mouseOnHud=false;
		}		
	}
	
	public void render(){
		Renderer.renderQuad(x0, y0, sizeX, sizeY, new float[]{0,0,1,0.6f});
		
		for(StructButton b:buttons){
			b.getTexture().bind();
			Renderer.renderQuad(x0 +4+ (buttons.indexOf(b))*((sizeX-sizeRes)/TypeStructures.values().length), y1+2, 16, 16, new float[]{1,1,1,1});
			b.getTexture().unbind();
			b.render();
		}
		
		
		for(TypeRessources res : ressources){
			res.getTexture().bind();
			Renderer.renderQuad(xRes + 1, y1 + ressources.indexOf(res)*sizeResIcon, sizeResIcon, sizeResIcon, new float[]{1,1,1,1});
			res.getTexture().unbind();
		}
		
		font.drawText(String.valueOf(Gestion.res.getWater()), xRes+1+sizeResIcon, y1+sizeResIcon*0);
		font.drawText(String.valueOf(Gestion.res.getOxygen()), xRes+1+sizeResIcon, y1+sizeResIcon*1);
		font.drawText(String.valueOf(Gestion.res.getIron()), xRes+1+sizeResIcon, y1+sizeResIcon*2);
		font.drawText(String.valueOf(Gestion.res.getElec()), xRes+1+sizeResIcon, y1+sizeResIcon*3);

	}
		

}