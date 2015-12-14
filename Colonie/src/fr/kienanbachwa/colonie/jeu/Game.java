package fr.kienanbachwa.colonie.jeu;

import org.lwjgl.opengl.GL11;

public class Game {

	
	public static float xScroll, yScroll;
	
	public Game(){
		
	}
	
	public void init(){
		
	}
	
	public void tick(){
		translateView(-0.25f, -0.25f);
	}
	
	public void render(){
		GL11.glTranslatef(xScroll, yScroll, 0);
		
	}
	
	public void translateView(float xa, float ya){
		xScroll += xa;
		yScroll += ya;
	}
	
	
}
