package fr.kienanbachwa.colonie.graphics.hud.panelObjects;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import fr.kienanbachwa.colonie.graphics.Texture;

public class OngletButton extends Button{

	boolean isSelected=false;
	String name;
	TrueTypeFont font;
	TrueTypeFont font2;
	
	public OngletButton(String name){
		super();
		this.name=name;
		this.texture=Texture.loadTexture("buttonText");
	}
	
	public void init(){
		Font awtFont = new Font("Times New Roman",Font.BOLD,10);
		font = new TrueTypeFont(awtFont,false);
		
		try{
			InputStream inputStream = ResourceLoader.getResourceAsStream("stan0753.ttf");
			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(8f);

			font2 = new TrueTypeFont(awtFont2, true);
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void update(int x, int y, int w, int h){
		super.update(x, y, w, h);
		if(clicked){
			this.select();
		}
	}
	
	public void render(){
		super.render();
		
		font2.drawString(x+w/2-font2.getWidth(name)/2, y + h/2 - font2.getHeight()/2, name);
		
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
}
