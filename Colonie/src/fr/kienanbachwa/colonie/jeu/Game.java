package fr.kienanbachwa.colonie.jeu;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import fr.benoitsepe.colonie.main.Gestion;
import fr.benoitsepe.colonie.structures.Structure;
import fr.kienanbachwa.colonie.graphics.Hud;
import fr.kienanbachwa.colonie.graphics.Renderer;

public class Game {

	
	public static float xScroll = 0, yScroll = 0;
	public static int mouseXGrid, mouseYGrid;
	public static int sizeMap = 50;
	Gestion gestion;
	
	private int dx1, dy1;
	private boolean mouseClicked;
	private int dx2;
	private int dy2;
	private int xa;
	private int ya;
	private int wheel;
	public static float zoom=1;
	private int middleX;
	private int middleY;
	private int speed=2;
	
	public Game(){
		gestion = new Gestion(sizeMap,sizeMap);
	}
	
	public void init(){
	}
	
	public void update(){
		translateViewWithKeyboard();
		translateViewWithMouse();
		gestion.update();
		
		mouseYGrid = (int) ( ( Component.height*Component.scale - Mouse.getY() + (-yScroll * Component.scale*zoom)) /Structure.tileSize/Component.scale/zoom);
		mouseXGrid = (int) ((Mouse.getX() + (-xScroll * Component.scale*zoom))/Structure.tileSize/Component.scale/zoom);
		
	}
	
	public void render(){
		middleX = Component.width/2;
		middleY = Component.height/2;

		zoom();
		System.out.println(xa);

		GL11.glTranslatef(xScroll, yScroll, 0);
		gestion.render();
		drawSelect(Mouse.getX(),Mouse.getY());	
		
	}
	
	public void zoom(){
		wheel = Mouse.getDWheel();
		if((Keyboard.isKeyDown(Keyboard.KEY_ADD) || wheel>0) && zoom<2){
			zoom+=0.01f;
		}
		if((Keyboard.isKeyDown(Keyboard.KEY_SUBTRACT) || wheel<0) && zoom>0.5f){
			zoom-=0.01f;
		}
		
		//GL11.glTranslatef(middleX, middleY, 0);	//Zoom au milieu mais c'est chiant pour la position de la souris
		GL11.glScaled(zoom, zoom, 0);
		//GL11.glTranslatef(-middleX, -middleY, 0);
		
		if(xScroll+xa>0){
			xScroll-=5;
		}
		
		if( -(xScroll+xa)>(sizeMap*Structure.tileSize - Component.width/zoom) ){
			xScroll+=5;
		}
		
		if(yScroll+ya>0){
			yScroll-=5;
		}
		if(-(yScroll+ya)>sizeMap*Structure.tileSize -Component.height/zoom){
			yScroll+=5;
		}
	}
	
	public void translateViewWithKeyboard(){
		xa=0; ya=0;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) xa=-speed;
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) xa=speed;
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)) ya=speed;
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) ya=-speed;
		
		if(xScroll+xa>0 || -(xScroll+xa)>(sizeMap*Structure.tileSize - Component.width/zoom) ){
			xa=0;
		}
		if(yScroll+ya>0 || -(yScroll+ya)>sizeMap*Structure.tileSize -Component.height/zoom){
			ya=0;
		}
		xScroll+=xa;
		yScroll+=ya;
	}
	
	public void translateViewWithMouse(){
		xa=0; ya=0;
		
		if(Mouse.isButtonDown(1) && !mouseClicked){
			dx2 = (int) ((Mouse.getX() + (-xScroll * Component.scale))/Component.scale);
			dy2 = (int) ( ( Component.height*Component.scale - Mouse.getY() + (-yScroll * Component.scale))/Component.scale);
			
		}else{
			dx1 = (int) ((Mouse.getX() + (-xScroll * Component.scale))/Component.scale);
			dy1 = (int) ( ( Component.height*Component.scale - Mouse.getY() + (-yScroll * Component.scale))/Component.scale);
		}
		
		if(Mouse.isButtonDown(1)){
			mouseClicked=true;
			xa = dx1 - dx2;
			ya = dy1 - dy2;
		}else{
			mouseClicked=false;
		}
		
		if(xScroll+xa>0 || -(xScroll+xa)>(sizeMap*Structure.tileSize - Component.width/zoom) ){
			xa=0;
		}
		if(yScroll+ya>0 || -(yScroll+ya)>sizeMap*Structure.tileSize -Component.height/zoom){
			ya=0;
		}
		
		xScroll+=xa;
		yScroll+=ya;

		
	}
	
	public void drawSelect(int mouseX, int mouseY){
		Renderer.renderQuad(mouseXGrid*Structure.tileSize, mouseYGrid*Structure.tileSize, Structure.tileSize, Structure.tileSize, new float[]{1,0,0,0.5f});
	}
}
