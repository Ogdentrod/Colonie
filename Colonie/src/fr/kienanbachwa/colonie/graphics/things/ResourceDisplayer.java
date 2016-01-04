package fr.kienanbachwa.colonie.graphics.things;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import fr.benoitsepe.colonie.main.Gestion;
import fr.benoitsepe.colonie.ressources.TypeRessources;

public class ResourceDisplayer extends Thing{

	private TrueTypeFont font;
	private TrueTypeFont font2;
	public ResourceDisplayer(TypeRessources typeRes){
		
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
//		font.drawText(String.valueOf(Gestion.res.getWater()), xRes+1+sizeResIcon, y1+sizeResIcon*0);
//		font.drawText(String.valueOf(Gestion.res.getOxygen()), xRes+1+sizeResIcon, y1+sizeResIcon*1);
//		font.drawText(String.valueOf(Gestion.res.getIron()), xRes+1+sizeResIcon, y1+sizeResIcon*2);
//		font.drawText(String.valueOf(Gestion.res.getElec()), xRes+1+sizeResIcon, y1+sizeResIcon*3);
	}
	
	
}
