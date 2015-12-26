package fr.kienanbachwa.colonie.graphics;

public abstract class Thing {
	Texture texture;
	
	public void init(){
	
	}
	
	public void update(){
		
	}
	
	public void render(){
		
	}
	
	public void setTexture(Texture texture){
		this.texture=texture;
	}
	
	public Texture getTexture(){
		return texture;
	}
	
}
