package fr.kienanbachwa.colonie.graphics.hud.panelObjects;

import java.util.Locale;

import org.newdawn.slick.TrueTypeFont;

import fr.benoitsepe.colonie.main.Gestion;
import fr.benoitsepe.colonie.ressources.TypeRessources;
import fr.kienanbachwa.colonie.graphics.Fonts;
import fr.kienanbachwa.colonie.graphics.Renderer;
import fr.kienanbachwa.colonie.graphics.Texture;

public class ResourceDisplayer extends Thing{
	String name;
	String quantity;
	Texture icon;
	TypeRessources typeRes;
	private TrueTypeFont font;
	
	public ResourceDisplayer(TypeRessources typeRes){
		this.typeRes=typeRes;
		this.name=typeRes.toString().toLowerCase(Locale.FRENCH);
		icon=typeRes.getTexture();
	}
	
	public void init(){
		font = new Fonts("fonts/stan0753.ttf", 8f).getFont();
	}
	
	public void render(){
		icon.bind();
		Renderer.renderQuad(x+5, y, h-2, h-2, new float[]{1,1,1,1});
		icon.unbind();
		
		font.drawString(x+h+5,y+h/2-font.getHeight()/2, String.valueOf(Gestion.res.getQuantity(typeRes)));
	}
	
	
}
