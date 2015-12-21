package fr.kienanbachwa.colonie.jeu;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import fr.benoitsepe.colonie.main.Gestion;
import fr.benoitsepe.colonie.structures.Structure;
import fr.kienanbachwa.colonie.graphics.Renderer;

public class Game {

	
	public static float xScroll = 0, yScroll = 0;
	public static int mouseXGrid, mouseYGrid;
	public static int sizeMap = 50;
	Gestion gestion;
	
	public Game(){
		gestion = new Gestion(sizeMap,sizeMap);
	}
	
	public void init(){
		
	}
	
	public void tick(){
		translateViewWithMouse();
		gestion.update();
		
		mouseYGrid = (int) ( ( Component.height*Component.scale - Mouse.getY() + (-yScroll * Component.scale)) /Structure.tileSize/Component.scale);
		mouseXGrid =(int) ((Mouse.getX() + (-xScroll * Component.scale))/Structure.tileSize/Component.scale);
		
	}
	
	public void render(){
		GL11.glTranslatef(xScroll, yScroll, 0);
		gestion.render();
		drawSelect(Mouse.getX(),Mouse.getY());
	}
	
	public void translateViewWithKeyBoard(){
		int xa=0, ya=0;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) xa=-1;
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) xa=1;
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)) ya=1;
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) ya=-1;
			
			if(xScroll+xa>0 || -(xScroll+xa)>(sizeMap*Structure.tileSize - Component.width) ){
				xa=0;
			}
			if(yScroll+ya>0 || -(yScroll+ya)>sizeMap*Structure.tileSize -Component.height){
				ya=0;
			}
			xScroll+=xa;
			yScroll+=ya;
		
	}
	
	public void translateViewWithMouse(){
		int xa=0, ya=0;
		int dx1 = Mouse.getDX();
		int dy1 = -Mouse.getDY();
		
		if(Mouse.isButtonDown(1)){
			int dx2 = Mouse.getDX();
			int dy2 = -Mouse.getDY();
			
			xa = dx1 - dx2;
			ya = dy1 - dy2;
		}
		
		if(xScroll+xa>0 || -(xScroll+xa)>(sizeMap*Structure.tileSize - Component.width) ){
			xa=0;
		}
		if(yScroll+ya>0 || -(yScroll+ya)>sizeMap*Structure.tileSize -Component.height){
			ya=0;
		}
		xScroll+=xa;
		yScroll+=ya;
		
	}
	
	public void drawSelect(int mouseX, int mouseY){
		//Renderer.renderBasicQuad(mouseXGrid*Structure.tileSize, mouseYGrid*Structure.tileSize, Structure.tileSize, Structure.tileSize, new float[]{1,0,0,0.5f});
		Renderer.renderBasicQuad(mouseXGrid*Structure.tileSize, mouseYGrid*Structure.tileSize, Structure.tileSize, Structure.tileSize, new float[]{1,0,0,0.5f});

	}
}
