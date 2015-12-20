package fr.kienanbachwa.colonie.jeu;

import org.lwjgl.opengl.GL11;

import fr.benoitsepe.colonie.main.Gestion;
import fr.benoitsepe.colonie.main.Structure;
import fr.benoitsepe.colonie.main.TypeStructures;
import fr.benoitsepe.colonie.structures.exterieur.Eolienne;

public class Game {

	
	public static float xScroll, yScroll;
	Gestion gestion;
	
	public Game(){
		gestion = new Gestion(50,50);
		
		Structure[][] structures = gestion.getStructures();
		for (int x=0; x<structures.length;x++) {
			for (int y=0; y<structures[0].length; y++) {
				gestion.creerStruct(TypeStructures.EOLIENNE, x, y);
			}
		}	
	}
	
	public void init(){
		
	}
	
	public void tick(){
		translateView(-0.5f, -0.5f);
	}
	
	public void render(){
		GL11.glTranslatef(xScroll, yScroll, 0);
		gestion.render();
	}
	
	public void translateView(float xa, float ya){
		xScroll += xa;
		yScroll += ya;
	}
	
	
}
