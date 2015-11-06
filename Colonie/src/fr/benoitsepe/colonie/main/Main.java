package fr.benoitsepe.colonie.main;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import fr.kienanbachwa.colonie.jeu.Fenetre;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		// Je t'ai mis un exemple de création d'une éolienne, lance le programme pour voir
		
		Gestion moteur = new Gestion(); // Init moteur
		
		
		moteur.creerStruct(TypeStructures.EOLIENNE, 0, 0); // Tu peux en créer plusieurs si tu veux, j'ai pas encore implémenté les autres structures
		moteur.creerStruct(TypeStructures.PANNEAU_SOLAIRE, 1, 0);
		moteur.creerStruct(TypeStructures.REFECTOIRE, 0, 1);
		
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Fenetre("Simple Slick Game"));
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
}
