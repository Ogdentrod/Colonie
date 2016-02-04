package fr.kienanbachwa.colonie.graphics.hud.panelObjects;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import fr.kienanbachwa.colonie.graphics.Fonts;
import fr.kienanbachwa.colonie.graphics.Texture;

public class OngletButton extends Button{

	boolean isSelected=false;
	String name;
	TrueTypeFont font;
	
	public OngletButton(String name){
		super();
		this.name=name;
		this.texture=Texture.loadTexture("buttonText2");
	}
	
	public void init(){
		font = new Fonts("fonts/stan0753.ttf", 8f).getFont();
	}
	
	public void update(int x, int y, int w, int h){
		super.update(x, y, w, h);
		if(clicked){
			this.select();
		}
	}
	
	public void render(){
		super.render();
		font.drawString(x+w/2-font.getWidth(name)/2, y + h/2 - font.getHeight()/2, name);
	}
	

}
