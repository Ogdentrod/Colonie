package fr.kienanbachwa.colonie.jeu;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import fr.benoitsepe.colonie.main.Gestion;
import fr.benoitsepe.colonie.main.Structure;
import fr.kienanbachwa.colonie.graphics.Renderer;

public class Game {

	
	public static float xScroll = 0, yScroll = 0;
	public static int mouseXGrid, mouseYGrid;
	Gestion gestion;
	
	public Game(){
		gestion = new Gestion(20,20);
	}
	
	public void init(){
		
	}
	
	public void tick(){
		//translateView(-0.5f, -0.5f);
		gestion.update();
		
		mouseYGrid = ((Component.height*Component.scale - Mouse.getY()))/Structure.tileSize/Component.scale;
		mouseXGrid = (Mouse.getX())/Structure.tileSize/Component.scale;
	}
	
	public void render(){
		GL11.glTranslatef(xScroll, yScroll, 0);
		gestion.render();
		drawSelect(Mouse.getX(),Mouse.getY());
	}
	
	public void translateView(float xa, float ya){
		xScroll += xa;
		yScroll += ya;
	}
	
	public void drawSelect(int mouseX, int mouseY){
		Renderer.renderBasicQuad(mouseXGrid*Structure.tileSize, mouseYGrid*Structure.tileSize, Structure.tileSize, Structure.tileSize, new float[]{1,0,0,0.5f});
	}
}
