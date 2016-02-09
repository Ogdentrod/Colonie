package fr.kienanbachwa.colonie.graphics.hud.dialogue;

import static org.lwjgl.opengl.GL11.glColor4f;

import fr.kienanbachwa.colonie.graphics.Color;
import fr.kienanbachwa.colonie.graphics.Fonts;
import fr.kienanbachwa.colonie.graphics.Renderer;
import fr.kienanbachwa.colonie.graphics.Texture;
import fr.kienanbachwa.colonie.graphics.TrueTypeFont;
import fr.kienanbachwa.colonie.graphics.hud.panelObjects.Button;

public class DialogueBox {	
	static TrueTypeFont font = new Fonts("fonts/stan0753.ttf", 12f).getFont();

	static Button confirm = new Button();
	static Button cancel = new Button();
	static Texture confirmTexture = Texture.loadTexture("check");
	static Texture cancelTexture = Texture.loadTexture("cross");
	
	public static void showInfo(String text, int x, int y){
		int h = font.getHeight(text);
		int w = font.getWidth(text);
		
//		boolean right = true; //if true, la bulle s'affichera vers la droite
//		boolean up = true;	//Pareil mais vers le haut
//		
//		if( font.getWidth(text)+x > Component.width ){
//			right=false;
//		}
//		if( font.getHeight(text)+y > Component.height){
//			up=false;
//		}
		
			Renderer.renderQuadSimple(x, y, w/4 +7, h/2, Color.DARKERRED);
			glColor4f(1, 1, 1, 1);
			font.drawString(x, y + h/2, Fonts.toWord(text), 0.5f, -0.5f);
	}
	
}
