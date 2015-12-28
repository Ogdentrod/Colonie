package fr.benoitsepe.colonie.elements;

import static org.lwjgl.opengl.GL11.*;


import java.util.Random;

import fr.kienanbachwa.colonie.graphics.Renderer;
import fr.kienanbachwa.colonie.graphics.Texture;

/**
 * @author Beno�t
 * 
 * Classe m�re de toutes les �l�ments
 * Contient les variables membres indispensables � chaque �l�ments
 * 
 * @precaution mettre les variables membres en protected et g�nerer les getter/setter
 *
 */
public class Element {
	protected int maintenance; // 0 = cass�, 100=neuf
	protected String nom; // nom de
	protected Etat etat;

	
	private int x;
	private int y;
	public static int tileSize = 16;
	Random random;
	float[] color;
	private Texture texture; 
	
	/**
	 * @param nom
	 * Le constructeur doit �tre appel� depuis la classe fille avec comme param�tre le nom du la structure
	 * Constructeur sans image: une image par d�faut est charg�e pour l'affichage
	 */
	public Element(String nom, int posX, int posY) {
		this.nom = nom;
		this.x = posX;
		this.y = posY;
		this.maintenance = 100;
		
		this.etat = Etat.QUEUED;
		
		
		/*
		random = new Random();
		color = new float[]{random.nextFloat(), random.nextFloat(), random.nextFloat(), 1.0f};
		*/
		color = new float[]{1,1,1,1};
		
		texture = Etat.valueOf(this.etat.toString()).getTexture();
		
	}




	/**
	 * @return the etat
	 */
	public Etat getEtat() {
		return etat;
	}




	/**
	 * @param etat the etat to set
	 */
	public void setEtat(Etat etat) {
		this.etat = etat;
		
		if (etat != Etat.QUEUED && etat != Etat.CONSTRUCTION) {
			texture = TypeElements.valueOf(nom.toUpperCase()).getTexture();
		} else {
			texture = Etat.valueOf(this.etat.toString()).getTexture();
		}
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
	
	public void setTexture(Texture text) {
		this.texture = text;
	}
	
	public Texture getTexture(){
		return texture;
	}

}
