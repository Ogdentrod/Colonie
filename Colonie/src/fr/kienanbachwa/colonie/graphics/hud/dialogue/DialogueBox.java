package fr.kienanbachwa.colonie.graphics.hud.dialogue;

import static org.lwjgl.opengl.GL11.glColor4f;

import org.lwjgl.input.Mouse;

import fr.kienanbachwa.colonie.graphics.Color;
import fr.kienanbachwa.colonie.graphics.Fonts;
import fr.kienanbachwa.colonie.graphics.Renderer;
import fr.kienanbachwa.colonie.graphics.Texture;
import fr.kienanbachwa.colonie.graphics.TrueTypeFont;
import fr.kienanbachwa.colonie.graphics.hud.panelObjects.Button;
import fr.kienanbachwa.colonie.jeu.Component;

public class DialogueBox {	
	static TrueTypeFont font = new Fonts("fonts/stan0753.ttf", 12f).getFont();

	
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
	
	public static boolean showConfirm(String text, int x, int y){
		int h = font.getHeight(text);
		int w = font.getWidth(text);
		Renderer.renderQuadSimple(x, y, w/4 +7, h/2, Color.DARKERRED);
		
		glColor4f(1, 1, 1, 1);
		font.drawString(x, y + h/2, Fonts.toWord(text), 0.5f, -0.5f);

		Button confirm = new Button();
		Button cancel = new Button();
		
		Texture confirmTexture = Texture.loadTexture("check");
		Texture cancelTexture = Texture.loadTexture("cross");
		
		confirm.setIcon(confirmTexture);
		cancel.setIcon(cancelTexture);
		
		confirm.init();
		cancel.init();
		
		confirm.update(x, y+h-10, w/2, 10);
		cancel.update(x+w/2, y+h-10, w/2, 10);
		
		if(confirm.clicked){
			return true;
		}
		
		if(cancel.clicked){
			return false;
		}
		return false;
	}
	
}
