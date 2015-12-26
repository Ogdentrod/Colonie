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
	
	private Font font;
	public static boolean mouseOnHud;
	public static TypeElements elementClicked = TypeElements.BATIMENT;
	
	int x,y,w,h;
	Panneau pan;
	public Hud(){
		try {
			font = new Font("res/minecraft_font.ttf", 6);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		w=Component.width;
		h=(Component.height/16)*4;
		pan = new Panneau(x,y-10,w,h);
	}
	
	public void init(){
		for(TypeElements e : TypeElements.values()){
			buttons.add(new ElemButton(e));				//Boutons d'élements
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
		
		for(ElemButton eb : buttons){
			pan.add(eb);
		}
	}
	
	public void update(){
		//Position en haut à gauche du hud
		x = 0;		
		y =(int)(Component.height-h);	
		w=Component.width;
				
//		for(ElemButton b:buttons){
//			b.update(x +(buttons.indexOf(b))*((w)/TypeElements.values().length), y, 16, 16);
//		}
		
		if(Mouse.getX() > x*Component.scale && Mouse.getX()<(x+w)*Component.scale && Mouse.getY() < (Component.height-y)*Component.scale && Mouse.getY()> (Component.height-y-h)*Component.scale){
			mouseOnHud=true;
		}else{
			mouseOnHud=false;
		}		
		
		pan.update(x,y+10,(w/4)*3,h);
	}
	
	public void render(){
		Renderer.renderQuad(x, y, w, h, new float[]{0,0,1,0.6f});
		pan.render();

//		for(ElemButton b:buttons){
//			b.getTexture().bind();
//			Renderer.renderQuad(x +(buttons.indexOf(b))*((w)/TypeElements.values().length), y, 16, 16, new float[]{1,1,1,1});
//			b.getTexture().unbind();
//			b.render();
//		}
	}
		

}