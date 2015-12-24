package fr.benoitsepe.colonie.elements;

import static org.lwjgl.opengl.GL11.*;


import java.util.Random;

import fr.kienanbachwa.colonie.graphics.Renderer;
import fr.kienanbachwa.colonie.graphics.Texture;

/**
 * @author Benoît
 * 
 * Classe mére de toutes les éléments
 * Contient les variables membres indispensables à chaque éléments
 * 
 * @precaution mettre les variables membres en protected et génerer les getter/setter
 *
 */
public class Element {
	protected int maintenance; // 0 = cassé, 100=neuf
	protected String nom; // nom de

	private int x;
	private int y;
	public static int tileSize = 16;
	Random random;
	float[] color;
	private Texture texture; 
	
	/**
	 * @param nom
	 * Le constructeur doit être appelé depuis la classe fille avec comme paramétre le nom du la structure
	 * Constructeur sans image: une image par défaut est chargée pour l'affichage
	 */
	public Element(String nom) {
		this.nom = nom;
		maintenance = 100;
		random = new Random();
		
		//color = new float[]{random.nextFloat(), random.nextFloat(), random.nextFloat(), 1.0f};
		color = new float[]{1,1,1,1};
		
		System.out.println(TypeElements.valueOf(nom.toUpperCase()));
		texture = TypeElements.valueOf(nom.toUpperCase()).getTexture();
		
	}




	public int getMaintenance() {
		return maintenance;
	}


	public void setMaintenance(int maintenance) {
		this.maintenance = maintenance;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	
	public void render(int x, int y){
		texture.bind();

		glBegin(GL_QUADS);
			Renderer.quadData(x*tileSize, y*tileSize, tileSize, tileSize, color);
		glEnd();
		
		texture.unbind();
	}




	public int getX() {
		return x;
	}




	public void setX(int x) {
		this.x = x;
	}




	public int getY() {
		return y;
	}




	public void setY(int y) {
		this.y = y;
	}
	
	public Texture getTexture(){
		return texture;
	}

}
