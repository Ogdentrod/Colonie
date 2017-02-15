package fr.kienanbachwa.colonie.jeu;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import fr.benoitsepe.colonie.main.Gestion;
import fr.benoitsepe.colonie.structures.Etat;
import fr.benoitsepe.colonie.structures.Structure;
import fr.benoitsepe.colonie.structures.TypeStructures;
import fr.benoitsepe.colonie.zone.Zone;
import fr.kienanbachwa.colonie.graphics.Color;
import fr.kienanbachwa.colonie.graphics.Renderer;
import fr.kienanbachwa.colonie.graphics.hud.Hud;
import fr.kienanbachwa.colonie.graphics.hud.dialogue.DialogueConfirm;

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
	private int speed=2;
	
	private int xMin;
	private int yMin;
	private int yMax;
	private int xMax;
	
	private Structure[][] structs;
	DialogueConfirm confirm;
	int confirmResult=0;
	private boolean showConfirmDialogue=false;
	
	public Game(){
		gestion = new Gestion(sizeMap,sizeMap);

	}
	
	public void init(){
	}
	
	public void update(){
		translateViewWithKeyboard();
		translateViewWithMouse();
		gestion.update();
		
		mouseYGrid = (int) ( ( Component.height*Component.scale - Mouse.getY() + (-yScroll * Component.scale*zoom)) /Zone.tileSize/Component.scale/zoom);
		mouseXGrid = (int) ((Mouse.getX() + (-xScroll * Component.scale*zoom))/Zone.tileSize/Component.scale/zoom);
		

	}
	
	public void render(){
		zoom();

		GL11.glTranslatef(xScroll, yScroll, 0);
		
		render_game();
		drawSelect(Mouse.getX(),Mouse.getY());	
		
		if(!gestion.getSelectedTiles().isEmpty()){
			if(confirm==null){
			}
			int posX=0;
			int posY=0;
			
			int rankY=0, rankX=0;
//			for(Structure e : gestion.getSelectedTiles()){
//				if( e.getX() > gestion.getSelectedTiles().get(rankX).getX() ){
//					rankX = gestion.getSelectedTiles().indexOf(e);
//				}
//			}
//			
//			for(Structure e : gestion.getSelectedTiles()){
//				if( e.getY() > gestion.getSelectedTiles().get(rankY).getY() ){
//					rankY = gestion.getSelectedTiles().indexOf(e);
//				}
//			}
			
			try{
			//posX = gestion.getSelectedTiles().get(0).getX()*Zone.tileSize + (gestion.getSelectedTiles().get(rankX).getX()*Zone.tileSize - gestion.getSelectedTiles().get(0).getX()*Zone.tileSize) /2;
			//posY = gestion.getSelectedTiles().get(0).getY()*Zone.tileSize + (gestion.getSelectedTiles().get(rankY).getY()*Zone.tileSize - gestion.getSelectedTiles().get(0).getY()*Zone.tileSize) /2;
			posX = gestion.getSelectedTiles().get(0).getX()*Zone.tileSize;
			posY = gestion.getSelectedTiles().get(0).getY()*Zone.tileSize;
			}catch(IndexOutOfBoundsException e){
				
			}
			confirmResult = DialogueConfirm.update("Prout",posX, posY);
			
			if( confirmResult == 1){
				for(Structure e : gestion.getSelectedTiles()){
					gestion.creerStruct(Hud.elementClicked, e.getX(), e.getY());
				}
				gestion.getSelectedTiles().clear();
				confirmResult=0;
				showConfirmDialogue=false;
			}else if( confirmResult == -1){
				gestion.getSelectedTiles().clear();
				confirmResult=0;
				showConfirmDialogue=false;
			}
			
		}
	}
	
	public void render_game() {
		structs = gestion.getStructures();
		
//		xMin = (int) (-Game.xScroll / Structure.tileSize);
//		yMin = (int) (-Game.yScroll / Structure.tileSize);
//
//		xMax = (((int) (-Game.xScroll / Structure.tileSize) + (Component.width / Structure.tileSize)+ 1) >= elems.length) ? elems.length : (int) (-Game.xScroll / Structure.tileSize) + (Component.width / Structure.tileSize) + 2;
//		yMax = (((int) (-Game.yScroll / Structure.tileSize) + (Component.height / Structure.tileSize)+ 1) >= elems[0].length) ? elems[0].length : (int) (-Game.yScroll / Structure.tileSize) + (Component.height / Structure.tileSize) + 2;

		xMin = (int) (-Game.xScroll / Zone.tileSize);
		yMin = (int) (-Game.yScroll / Zone.tileSize);

		xMax = (((int) (-Game.xScroll / Zone.tileSize) + (Component.width / Zone.tileSize/Game.zoom)+ 3) >= structs.length) ? structs.length : (int) (-Game.xScroll / Zone.tileSize) + (int)(Component.width / Zone.tileSize/Game.zoom) + 3;
		yMax = (((int) (-Game.yScroll / Zone.tileSize) + (Component.height / Zone.tileSize/Game.zoom)+ 3) >= structs[0].length) ? structs[0].length : (int) (-Game.yScroll / Zone.tileSize) + (int)(Component.height / Zone.tileSize/Game.zoom) + 3;
		
		for (int x = xMin; x < xMax; x++) {
			for (int y = yMin; y < yMax; y++) {
				if (structs[x][y] != null){
					structs[x][y].render(x, y);			

					
				if( TypeStructures.valueOf( structs[x][y].getNom().toUpperCase()) == TypeStructures.MUR ){

					if(x==0){
						structs[x][y].getTexture().bind();
						Renderer.renderQuadSheet(x*16, y*16, 16, 16, Color.WHITE, 0, 1);
						structs[x][y].getTexture().unbind();
						continue;
					}
					if(y==0){
						structs[x][y].getTexture().bind();
						Renderer.renderQuadSheet(x*16, y*16, 16, 16, Color.WHITE, 0, 1);
						structs[x][y].getTexture().unbind();
						continue;
					}
					if(x==structs.length){
						structs[x][y].getTexture().bind();
						Renderer.renderQuadSheet(x*16, y*16, 16, 16, Color.WHITE, 0, 1);
						structs[x][y].getTexture().unbind();
						continue;
					}
					if(y==structs[0].length){
						structs[x][y].getTexture().bind();
						Renderer.renderQuadSheet(x*16, y*16, 16, 16, Color.WHITE, 0, 1);
						structs[x][y].getTexture().unbind();
						continue;
					}
					
					/*
					[ul][uu][ur]
					[ml][XY][mr]
					[dl][dd][dr]				
					 */
					
					boolean ul=false, uu=false, ur=false, ml=false, mr=false, dl=false, dd=false, dr=false;
					if(TypeStructures.valueOf(structs[x-1][y-1].getNom().toUpperCase()) == TypeStructures.MUR || TypeStructures.valueOf(structs[x-1][y-1].getNom().toUpperCase()) == TypeStructures.PORTE)
						ul=true;
					
					if(TypeStructures.valueOf(structs[x][y-1].getNom().toUpperCase()) == TypeStructures.MUR || TypeStructures.valueOf(structs[x][y-1].getNom().toUpperCase()) == TypeStructures.PORTE)
						uu=true;
					
					if(TypeStructures.valueOf(structs[x+1][y-1].getNom().toUpperCase()) == TypeStructures.MUR || TypeStructures.valueOf(structs[x+1][y-1].getNom().toUpperCase()) == TypeStructures.PORTE)
						ur=true;
					
					if(TypeStructures.valueOf(structs[x-1][y].getNom().toUpperCase()) == TypeStructures.MUR || TypeStructures.valueOf(structs[x-1][y].getNom().toUpperCase()) == TypeStructures.PORTE)
						ml=true;
					
					if(TypeStructures.valueOf(structs[x+1][y].getNom().toUpperCase()) == TypeStructures.MUR || TypeStructures.valueOf(structs[x+1][y].getNom().toUpperCase()) == TypeStructures.PORTE)
						mr=true;
					
					if(TypeStructures.valueOf(structs[x-1][y+1].getNom().toUpperCase()) == TypeStructures.MUR || TypeStructures.valueOf(structs[x-1][y+1].getNom().toUpperCase()) == TypeStructures.PORTE)
						dl=true;
					
					if(TypeStructures.valueOf(structs[x][y+1].getNom().toUpperCase()) == TypeStructures.MUR || TypeStructures.valueOf(structs[x][y+1].getNom().toUpperCase()) == TypeStructures.PORTE)
						dd=true;
					
					if(TypeStructures.valueOf(structs[x+1][y+1].getNom().toUpperCase()) == TypeStructures.MUR || TypeStructures.valueOf(structs[x+1][y+1].getNom().toUpperCase()) == TypeStructures.PORTE)
						dr=true;
					
					

						
						if( uu ){
							structs[x][y].getTexture().bind();
							Renderer.renderQuadSheet(x*16, y*16, 16, 16, Color.WHITE, 0, 1);
							structs[x][y].getTexture().unbind();
						}
						if( dd ){
							structs[x][y].getTexture().bind();
							Renderer.renderQuadSheet(x*16, y*16, 16, 16, Color.WHITE, 2, 1);
							structs[x][y].getTexture().unbind();
						}
						if( mr ){
							structs[x][y].getTexture().bind();
							Renderer.renderQuadSheet(x*16, y*16, 16, 16, Color.WHITE, 1, 0);
							structs[x][y].getTexture().unbind();
						}
						if( ml ){
							structs[x][y].getTexture().bind();
							Renderer.renderQuadSheet(x*16, y*16, 16, 16, Color.WHITE, 1, 2);
							structs[x][y].getTexture().unbind();
						}
						
						if( dd && mr ){
							structs[x][y].getTexture().bind();
							Renderer.renderQuadSheet(x*16, y*16, 16, 16, Color.WHITE, 0, 0);
							structs[x][y].getTexture().unbind();
						}
						if( dd && ml ){
							structs[x][y].getTexture().bind();
							Renderer.renderQuadSheet(x*16, y*16, 16, 16, Color.WHITE, 2, 0);
							structs[x][y].getTexture().unbind();
						}
						if( uu && mr ){
							structs[x][y].getTexture().bind();
							Renderer.renderQuadSheet(x*16, y*16, 16, 16, Color.WHITE, 0, 2);
							structs[x][y].getTexture().unbind();
						}
						if( uu && ml ){
							structs[x][y].getTexture().bind();
							Renderer.renderQuadSheet(x*16, y*16, 16, 16, Color.WHITE, 2, 2);
							structs[x][y].getTexture().unbind();
						}
					}
					
				if (structs[x][y].getEtat() == Etat.QUEUED || structs[x][y].getEtat() == Etat.CONSTRUCTION) {
					Etat.valueOf( structs[x][y].getEtat().toString() ).getTexture().bind();
					Renderer.renderQuad(x*16, y*16, 16, 16, Color.WHITE);
					Etat.valueOf( structs[x][y].getEtat().toString() ).getTexture().unbind();

				}
					
				}
			}
		}
		
		renderSelectedTiles();

	}
	
	public void renderSelectedTiles(){
		//if(Mouse.isButtonDown(0) && !Hud.mouseOnHud){	
		if(!gestion.getSelectedTiles().isEmpty()){
			for(Structure e : gestion.getSelectedTiles()){
				Hud.elementClicked.getTexture().bind();
				Renderer.renderQuad(e.getX()*16, e.getY()*16, 16, 16, new float[]{0,0,1,0.5f});
				Hud.elementClicked.getTexture().unbind();
			}
		}
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
		
		if( -(xScroll+xa)>(sizeMap*Zone.tileSize - Component.width/zoom) ){
			xScroll+=5;
		}
		
		if(yScroll+ya>0){
			yScroll-=5;
		}
		if(-(yScroll+ya)>sizeMap*Zone.tileSize -Component.height/zoom){
			yScroll+=5;
		}
	}
	
	public void translateViewWithKeyboard(){
		xa=0; ya=0;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) xa=-speed;
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) xa=speed;
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)) ya=speed;
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) ya=-speed;
		
		if(xScroll+xa>0 || -(xScroll+xa)>(sizeMap*Zone.tileSize - Component.width/zoom) ){
			xa=0;
		}
		if(yScroll+ya>0 || -(yScroll+ya)>sizeMap*Zone.tileSize -Component.height/zoom){
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
		
		if(xScroll+xa>0 || -(xScroll+xa)>(sizeMap*Zone.tileSize - Component.width/zoom) ){
			xa=0;
		}
		if(yScroll+ya>0 || -(yScroll+ya)>sizeMap*Zone.tileSize -Component.height/zoom){
			ya=0;
		}
		
		xScroll+=xa;
		yScroll+=ya;

		
	}
	
	public void drawSelect(int mouseX, int mouseY){
		Renderer.renderQuad(mouseXGrid*Zone.tileSize, mouseYGrid*Zone.tileSize, Zone.tileSize, Zone.tileSize, new float[]{1,0,0,0.5f});
	}
}
