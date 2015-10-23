package fr.kienanbachwa.colonie.affichage;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Fenetre extends BasicGame
{
	public GameContainer gc;
	String texte = "Hello";
	public Fenetre(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		this.gc=gc;
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
		g.drawString(texte, 200, 10);
	}
}