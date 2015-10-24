package fr.kienanbachwa.colonie.affichage;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Hud {
	
	private Image image;
	
	public void init() throws SlickException{
		//this.image= new Image("cheminImage");
	}
	
	public void render(Graphics g){
		g.resetTransform();
		//g.drawImage(image,posX,posY);
	}

}
