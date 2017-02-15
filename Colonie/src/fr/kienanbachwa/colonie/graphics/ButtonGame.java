package fr.kienanbachwa.colonie.graphics;

import org.lwjgl.input.Mouse;

import fr.kienanbachwa.colonie.graphics.hud.Hud;
import fr.kienanbachwa.colonie.graphics.hud.panelObjects.Thing;
import fr.kienanbachwa.colonie.jeu.Component;
import fr.kienanbachwa.colonie.jeu.Game;

public class ButtonGame extends Thing{

	public boolean clicked = false;
	public boolean hovered = false;
	protected boolean isSelected;
	Texture icon;
	
	public ButtonGame(){
		super();
		this.texture=Texture.loadTexture("buttonText");
	}
	
	public void update(int x, int y, int w, int h){
		super.update(x, y, w, h);
		
		if( ((Mouse.getX() + (-Game.xScroll * Component.scale*Game.zoom))/Component.scale/Game.zoom)>x && ((Mouse.getX() + (-Game.xScroll * Component.scale*Game.zoom))/Component.scale/Game.zoom)<x+w && ( ( Component.height*Component.scale - Mouse.getY() + (-Game.yScroll * Component.scale*Game.zoom))/Component.scale/Game.zoom)>y && ( ( Component.height*Component.scale - Mouse.getY() + (-Game.yScroll * Component.scale*Game.zoom))/Component.scale/Game.zoom) <y+h){
			hovered=true;
		}else{
			hovered=false;
		}
		
		if(Mouse.isButtonDown(0) && hovered){
			this.clicked=true;
		}else{
			this.clicked=false;
		}
		
		if(clicked){
			this.select();
		}
	}
	
	public void render(){
		if(this.getTexture()!=null) this.getTexture().bind();
		Renderer.renderQuad(x, y, w, h, new float[]{1,1,1,1});
		if(this.getTexture()!=null) this.getTexture().unbind();
		
		if(hovered){
			Renderer.renderQuad(x,y,w,h, new float[]{0,0,0,0.2f});
			
			
		}
		
		if(hovered && clicked){
			Renderer.renderQuad(x,y,w,h, new float[]{0,0,0,0.4f});
		}
		
		if(icon!=null){
			icon.bind();
			Renderer.renderQuad(x + w/2 - (Hud.hudTileSize/4), y + h/2 - (Hud.hudTileSize/4), Hud.hudTileSize/2, Hud.hudTileSize/2, Color.WHITE);
			icon.unbind();
		}
		
	}
	
	public boolean isClicked(){
		return clicked;
	}
	
	public boolean isHovered(){
		return hovered;
	}
	
	public void select(){
		isSelected=true;
	}
	
	public void deselect(){
		isSelected=false;

	}
	
	public boolean isSelected(){
		return isSelected;
	}
	
	public void setIcon(Texture texture){
		this.icon=texture;
	}
}
