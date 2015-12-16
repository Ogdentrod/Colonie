package fr.kienanbachwa.colonie.jeu;

import org.lwjgl.opengl.GL11;

import fr.benoitsepe.colonie.main.Gestion;

public class Game {

	
	public static float xScroll, yScroll;
	Gestion gestion;
	
	public Game(){
		gestion = new Gestion(100,100);
	}
	
	public void init(){
		
	}
	
	public void tick(){
		translateView(-0.5f, -0.5f);
	}
	
	public void render(){
		GL11.glTranslatef(xScroll, yScroll, 0);
		gestion.render();
	}
	
	public void translateView(float xa, float ya){
		xScroll += xa;
		yScroll += ya;
	}
	
	
}
