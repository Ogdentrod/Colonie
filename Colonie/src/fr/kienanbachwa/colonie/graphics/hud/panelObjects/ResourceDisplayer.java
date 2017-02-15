package fr.kienanbachwa.colonie.graphics.hud.panelObjects;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import org.newdawn.slick.util.ResourceLoader;

import fr.benoitsepe.colonie.main.Gestion;
import fr.benoitsepe.colonie.ressources.TypeRessources;
import fr.kienanbachwa.colonie.graphics.Fonts;
import fr.kienanbachwa.colonie.graphics.Renderer;
import fr.kienanbachwa.colonie.graphics.Texture;
import fr.kienanbachwa.colonie.graphics.TrueTypeFont;


public class ResourceDisplayer extends Thing{
	String name;
	String quantity;
	Texture icon;
	TypeRessources typeRes;
	private TrueTypeFont font;
	Font awtFont;

	
	public ResourceDisplayer(TypeRessources typeRes){
		this.typeRes=typeRes;
		this.name=typeRes.toString().toLowerCase(Locale.FRENCH);
		icon=typeRes.getTexture();
	}
	
	public void init(){

		InputStream inputStream = ResourceLoader.getResourceAsStream("fonts/stan0753.ttf");
		try {
			awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont = awtFont.deriveFont(16f);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		font = new TrueTypeFont(awtFont, true);
		
	}
	
	public void render(){
		icon.bind();
		Renderer.renderQuad(x+5, y, h-2, h-2, new float[]{1,1,1,1});
		icon.unbind();
		
		//font.drawString(x+h+5,y+h/2-font.getHeight()/2, String.valueOf(Gestion.res.getQuantity(typeRes)));
		font.drawString(x+h+5, y+h/2+font.getHeight()/4, String.valueOf(Gestion.res.getQuantity(typeRes)), 0.5f, -0.5f );

	}
	
	
}
