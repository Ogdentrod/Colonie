package fr.benoitsepe.colonie.main;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import fr.kienanbachwa.colonie.jeu.Jeu;

public class Main {

	public static void main(String[] args) {

		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Jeu("Simple Slick Game"));
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
}
