package fr.kienanbachwa.colonie.graphics.hud.dialogue;

import static org.lwjgl.opengl.GL11.glColor4f;

import org.lwjgl.input.Mouse;

import fr.benoitsepe.colonie.zone.Zone;
import fr.kienanbachwa.colonie.graphics.ButtonGame;
import fr.kienanbachwa.colonie.graphics.Color;
import fr.kienanbachwa.colonie.graphics.Fonts;
import fr.kienanbachwa.colonie.graphics.Renderer;
import fr.kienanbachwa.colonie.graphics.Texture;
import fr.kienanbachwa.colonie.graphics.TrueTypeFont;
import fr.kienanbachwa.colonie.jeu.Component;

public class DialogueConfirm {	
	static TrueTypeFont font = new Fonts("fonts/stan0753.ttf", 12f).getFont();

	static ButtonGame confirm = new ButtonGame();
	static ButtonGame cancel = new ButtonGame();
	static Texture confirmTexture = Texture.loadTexture("check");
	static Texture cancelTexture = Texture.loadTexture("cross");
	boolean mouseOnDialogue = false;
	
	String text;
	
	public static int update(String text, int x, int y){

//		if(Mouse.getX() > x*Component.scale && Mouse.getX()<(x+w)*Component.scale && Mouse.getY() < (Component.height-y)*Component.scale && Mouse.getY()> (Component.height-y-h)*Component.scale){
//			mouseOnDialogue=true;
//		}else{
//			mouseOnDialogue=false;
//		}
		
		int h = font.getHeight(text);
		int w = font.getWidth(text);
		confirm.setIcon(confirmTexture);
		cancel.setIcon(cancelTexture);
		
		confirm.update(x, y+h-10, (w/4 +7)/2, 10);
		cancel.update(x+((w/4 +7)/2), y+h-10, (w/4 +7)/2, 10);

		Renderer.renderQuadSimple(x, y, w/4 +7, h/2, Color.DARKERRED);
		font.drawString(x, y + h/2, Fonts.toWord(text), 0.5f, -0.5f);
		
		confirm.render();
		cancel.render();
		
		if(confirm.clicked){
			return 1;
		}
		
		if(cancel.clicked){
			return -1;
		}
		return 0;
	}	
}
