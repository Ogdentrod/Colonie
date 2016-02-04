package fr.benoitsepe.colonie.structures;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;

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
public class Structure {
	protected int maintenance; // 0 = cassé, 100=neuf
	protected String nom; // nom de
	protected Etat etat;
	protected long tempsConstruction;




	private int x;
	private int y;
	public static int tileSize = 16;

	float[] color;
	private Texture texture; 

	/**
	 * @param nom
	 * Le constructeur doit être appelé depuis la classe fille avec comme paramétre le nom du la structure
	 * Constructeur sans image: une image par défaut est chargée pour l'affichage
	 */
	public Structure(String nom, long tempsConstruction, int posX, int posY, Etat etat) {
		this.nom = nom;
		this.x = posX;
		this.y = posY;
		this.tempsConstruction = tempsConstruction;

		this.maintenance = 100;

		this.etat = etat;



		color = new float[]{1,1,1,1};

			texture = TypeStructures.valueOf(nom.toUpperCase()).getTexture();


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

//		if (etat != Etat.QUEUED && etat != Etat.CONSTRUCTION) {
//			texture = TypeElements.valueOf(nom.toUpperCase()).getTexture();
//		} else {
//			texture = Etat.valueOf(this.etat.toString()).getTexture();
//		}


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


	/**
	 * @return the tempsConstruction
	 */
	public long getTempsConstruction() {
		return tempsConstruction;
	}

	public void setY(int y) {
		this.y = y;
	}


	public Texture getTexture(){
		return texture;
	}

}
