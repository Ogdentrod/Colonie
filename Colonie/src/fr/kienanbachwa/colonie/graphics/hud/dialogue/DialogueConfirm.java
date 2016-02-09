package fr.kienanbachwa.colonie.graphics.hud.dialogue;

import static org.lwjgl.opengl.GL11.glColor4f;

import fr.kienanbachwa.colonie.graphics.Color;
import fr.kienanbachwa.colonie.graphics.Fonts;
import fr.kienanbachwa.colonie.graphics.Renderer;
import fr.kienanbachwa.colonie.graphics.Texture;
import fr.kienanbachwa.colonie.graphics.TrueTypeFont;
import fr.kienanbachwa.colonie.graphics.hud.panelObjects.Button;

public class DialogueConfirm {	
	static TrueTypeFont font = new Fonts("fonts/stan0753.ttf", 12f).getFont();

	static Button confirm = new Button();
	static Button cancel = new Button();
	static Texture confirmTexture = Texture.loadTexture("check");
	static Texture cancelTexture = Texture.loadTexture("cross");
	
	int h;
	int w;
	String text;
	
	public DialogueConfirm(String text, int x, int y){
		this.text=text;
		this.h = font.getHeight(text);
		this.w = font.getWidth(text);
		
		glColor4f(1, 1, 1, 1);

		confirm.setIcon(confirmTexture);
		cancel.setIcon(cancelTexture);
		
		confirm.init();
		cancel.init();
		

		

	}
	
	public int update(int x, int y){
		Renderer.renderQuadSimple(x, y, w/4 +7, h/2, Color.DARKERRED);
		font.drawString(x, y + h/2, Fonts.toWord(text), 0.5f, -0.5f);
		
		confirm.render();
		cancel.render();
		
		confirm.update(x, y+h-10, (w/4 +7)/2, 10);
		cancel.update(x+((w/4 +7)/2), y+h-10, (w/4 +7)/2, 10);


		
		if(confirm.clicked){
			return 1;
		}
		
		if(cancel.clicked){
			return -1;
		}
		return 0;
	}
	
	public void render(){
		
	}
	
}
