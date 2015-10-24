package fr.kienanbachwa.colonie.affichage;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Fenetre extends BasicGame
{
	public GameContainer gc;
	String texte = "Surprise!";
	Image background;
	private AppGameContainer app;
	
	public Fenetre(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		this.gc=gc;
		background = new Image("ressources/cage.jpg");
		if(gc instanceof AppGameContainer){
			
		}
	}

	public void keyReleased(int key, char c){
		if (Input.KEY_ESCAPE==key){
			gc.exit();
		}
	}
	
	public void keyPressed(int key, char c){
			if(key==14){
				if(texte.length()>0)
				texte=texte.substring(0,texte.length()-1);
			}else{
				texte=texte+c;
			}
	}
	
	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		background.draw(0,0,640,480);
		g.setColor(Color.red);
		g.scale(1.5f,1.5f);
		g.drawString(texte, 180, 80);
	}
}