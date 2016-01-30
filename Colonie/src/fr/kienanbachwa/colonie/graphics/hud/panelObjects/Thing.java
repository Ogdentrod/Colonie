package fr.kienanbachwa.colonie.graphics.hud.panelObjects;

import fr.kienanbachwa.colonie.graphics.Texture;

public abstract class Thing {
	Texture texture;
	protected int x;
	protected int y;
	protected int w;
	protected int h;
	
	public void init(){
	}
	
	public void update(int x, int y, int w, int h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;	
	}
	
	public void render(){
		
	}
	
	public void setTexture(Texture texture){
		this.texture=texture;
	}
	
	public Texture getTexture(){
		return texture;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getW(){
		return w;
	}
	
	public int getH(){
		return h;
	}
}
