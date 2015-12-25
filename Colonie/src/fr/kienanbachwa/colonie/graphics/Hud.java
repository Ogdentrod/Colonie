package fr.kienanbachwa.colonie.graphics;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import fr.benoitsepe.colonie.elements.TypeElements;
import fr.benoitsepe.colonie.main.Gestion;
import fr.benoitsepe.colonie.ressources.TypeRessources;
import fr.kienanbachwa.colonie.jeu.Component;
import fr.kienanbachwa.colonie.jeu.Game;

public class Hud {
	
	List<ElemButton> buttons = new ArrayList<ElemButton>();
	List<TypeRessources> ressources = new ArrayList<TypeRessources>();
	
	private int sizeX, sizeY;	//Taille du hud
	private int x0,y0;		//position d'affichage du hud
	private int x1;			//position d'affichage des ressources
	private int sizeRes = 60;	//taille de l'affichage des ressources
	private int sizeResIcon;
	private Font font;
	private int y1;
	public static boolean mouseOnHud;
	public static TypeElements elementClicked = TypeElements.BATIMENT;
	
	
	public Hud(){
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
		for(TypeElements e : TypeElements.values()){
			buttons.add(new ElemButton(e));
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
		x0 = (int)(-Game.xScroll +(Component.width/2 -sizeX/2));
		y0 =(int)(Component.height-sizeY-Game.yScroll);
		
		y1=y0+10;
		
		x1 = x0 +4+ TypeElements.values().length*((sizeX-sizeRes)/TypeElements.values().length);
		
		for(ElemButton b:buttons){
			b.update(x0 +4+ (buttons.indexOf(b))*((sizeX-sizeRes)/TypeElements.values().length), y1+2, 16, 16);
			
		}
		
		if(Mouse.getX() > x0*Component.scale && Mouse.getX()<(x0+sizeX)*Component.scale && Mouse.getY()<y0+sizeY && Mouse.isInsideWindow()){
			mouseOnHud=true;
		}else{
			mouseOnHud=false;
		}		
		System.out.println(Mouse.getY());
	}
	
	public void render(){
		Renderer.renderQuad(x0, y0, sizeX, sizeY, new float[]{0,0,1,0.6f});
		
		for(ElemButton b:buttons){
			b.getTexture().bind();
			Renderer.renderQuad(x0 +4+ (buttons.indexOf(b))*((sizeX-sizeRes)/TypeElements.values().length), y1+2, 16, 16, new float[]{1,1,1,1});
			b.getTexture().unbind();
			b.render();
		}
		
		
		for(TypeRessources res : ressources){
			res.getTexture().bind();
			Renderer.renderQuad(x1 + 1, y1 + ressources.indexOf(res)*sizeResIcon, sizeResIcon, sizeResIcon, new float[]{1,1,1,1});
			res.getTexture().unbind();
		}
		
		font.drawText(String.valueOf(Gestion.res.getWater()), x1+1+sizeResIcon, y1+sizeResIcon*0);
		font.drawText(String.valueOf(Gestion.res.getOxygen()), x1+1+sizeResIcon, y1+sizeResIcon*1);
		font.drawText(String.valueOf(Gestion.res.getIron()), x1+1+sizeResIcon, y1+sizeResIcon*2);
		font.drawText(String.valueOf(Gestion.res.getElec()), x1+1+sizeResIcon, y1+sizeResIcon*3);

	}
		

}