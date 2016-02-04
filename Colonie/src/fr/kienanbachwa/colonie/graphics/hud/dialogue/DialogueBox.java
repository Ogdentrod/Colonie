package fr.kienanbachwa.colonie.graphics.hud.dialogue;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;

import fr.kienanbachwa.colonie.graphics.Color;
import fr.kienanbachwa.colonie.graphics.Fonts;
import fr.kienanbachwa.colonie.graphics.Renderer;
import fr.kienanbachwa.colonie.jeu.Component;

public class DialogueBox {	
	static TrueTypeFont font = new Fonts("fonts/stan0753.ttf", 8f).getFont();

	
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
		
			Renderer.renderQuadSimple((int)(Mouse.getX()/Component.scale), (int)(Component.height-(Mouse.getY()/Component.scale)), font.getWidth(text)+10, font.getHeight(text)+10, Color.BLUE);
		
			font.drawString(Mouse.getX()/Component.scale +5, Component.height-(Mouse.getY()/Component.scale) +5, text);
			//Renderer.quadData(x+5, y+5, font.getWidth(text), font.getHeight(text), Color.BLACK);
	}
	
}
