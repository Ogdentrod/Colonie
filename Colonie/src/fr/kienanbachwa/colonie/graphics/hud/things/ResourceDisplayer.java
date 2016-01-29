package fr.kienanbachwa.colonie.graphics.hud.things;

import java.awt.Font;
import java.io.InputStream;
import java.util.Locale;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import fr.benoitsepe.colonie.main.Gestion;
import fr.benoitsepe.colonie.ressources.TypeRessources;
import fr.kienanbachwa.colonie.graphics.Renderer;
import fr.kienanbachwa.colonie.graphics.Texture;

public class ResourceDisplayer extends Thing{
	private TrueTypeFont font;
	private TrueTypeFont font2;
	String name;
	String quantity;
	Texture icon;
	TypeRessources typeRes;
	
	public ResourceDisplayer(TypeRessources typeRes){
		this.typeRes=typeRes;
		this.name=typeRes.toString().toLowerCase(Locale.FRENCH);
		icon=typeRes.getTexture();
	}
	
	public void init(){
		Font awtFont = new Font("Times New Roman",Font.BOLD,8);
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
	
	public void render(){
		icon.bind();
		Renderer.renderQuad(x+5, y, h-2, h-2, new float[]{1,1,1,1});
		icon.unbind();
		
		font2.drawString(x+h+5,y+h/2-font2.getHeight()/2, String.valueOf(Gestion.res.getQuantity(typeRes)));
	}
	
	
}
