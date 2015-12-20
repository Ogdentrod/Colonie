package fr.benoitsepe.colonie.main;

import static org.lwjgl.opengl.GL11.*;


import java.util.Random;

import fr.kienanbachwa.colonie.graphics.Renderer;
import fr.kienanbachwa.colonie.graphics.Texture;

/**
 * @author Beno�t
 * 
 * Classe m�re de toutes les structures int�rieurs ou ext�rieurs
 * Contient les variables membres indispensables � chaque structure
 * 
 * @precaution mettre les variables membres en protected et g�nerer les getter/setter
 *
 */
public class Structure implements IStructure {
	protected Etat etat; // RUNNING ou STOP
	protected int maintenance; // 0 = cass�, 100=neuf
	protected String nom; // nom de la structure

	int x;
	int y;
	public static int tileSize = 16;
	Random random;
	float[] color;
	private Texture texture; 
	
	/**
	 * @param nom
	 * Le constructeur doit �tre appel� depuis la classe fille avec comme param�tre le nom du la structure
	 * Constructeur sans image: une image par d�faut est charg�e pour l'affichage
	 */
	public Structure(String nom) {
		this.nom = nom;
		etat = Etat.RUNNING;
		maintenance = 100;
		random = new Random();
		
		//color = new float[]{random.nextFloat(), random.nextFloat(), random.nextFloat(), 1.0f};
		color = new float[]{1,1,1,1};
		
		System.out.println(TypeStructures.valueOf(nom.toUpperCase()));
		texture = TypeStructures.valueOf(nom.toUpperCase()).getTexture();
		
	}


	public Etat getEtat() {
		return etat;
	}


	public void setEtat(Etat etat) {
		this.etat = etat;
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


	@Override
	public void utiliser(Ressources res) {
		System.out.println("Je ne suis pas impl�ment� ! " + nom);
	}
	
	public void connect(Structure struct){
		
	}
	
	public void render(int x, int y){
		texture.bind();

		glBegin(GL_QUADS);
			Renderer.quadData(x*tileSize, y*tileSize, tileSize, tileSize, color);
		glEnd();
		
		texture.unbind();
	}

}
