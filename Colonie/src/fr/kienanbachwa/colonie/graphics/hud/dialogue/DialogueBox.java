package fr.kienanbachwa.colonie.graphics.hud.dialogue;

import static org.lwjgl.opengl.GL11.glColor4f;

import org.lwjgl.input.Mouse;
import fr.kienanbachwa.colonie.graphics.TrueTypeFont;

import fr.kienanbachwa.colonie.graphics.Color;
import fr.kienanbachwa.colonie.graphics.Fonts;
import fr.kienanbachwa.colonie.graphics.Renderer;
import fr.kienanbachwa.colonie.jeu.Component;

public class DialogueBox {	
	static TrueTypeFont font = new Fonts("fonts/stan0753.ttf", 15f).getFont();

	
	public static void showInfo(String text, int x, int y){
//		boolean right = true; //if true, la bulle s'affichera vers la droite
//		boolean up = true;	//Pareil mais vers le haut
//		
//		if( font.getWidth(text)+x > Component.width ){
//			right=false;
//		}
//		if( font.getHeight(text)+y > Component.height){
//			up=false;
//		}
		
			Renderer.renderQuadSimple((int)(Mouse.getX()/Component.scale) +4, (int)(Component.height-(Mouse.getY()/Component.scale)), font.getWidth(text)/4 +7, font.getHeight(text)/2, Color.DARKERRED);
			glColor4f(1, 1, 1, 1);
			font.drawString((int)(Mouse.getX()/Component.scale) +4, (int)(Component.height-(Mouse.getY()/Component.scale)) + font.getHeight(text)/2, text, 0.5f, -0.5f);
	}
	
}
