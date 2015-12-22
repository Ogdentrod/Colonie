package fr.benoitsepe.colonie.structures;

import static org.lwjgl.opengl.GL11.*;

import java.util.List;
import java.util.Random;

import fr.benoitsepe.colonie.main.Coordonnees;
import fr.benoitsepe.colonie.main.Etat;
import fr.benoitsepe.colonie.main.IStructure;
import fr.benoitsepe.colonie.ressources.Ressources;
import fr.kienanbachwa.colonie.graphics.Renderer;
import fr.kienanbachwa.colonie.graphics.Texture;

/**
 * @author Benoît
 * 
 * Classe mére de toutes les structures intérieurs ou extérieurs
 * Contient les variables membres indispensables à chaque structure
 * 
 * @precaution mettre les variables membres en protected et génerer les getter/setter
 *
 */
public class Structure implements IStructure {
	protected Etat etat; // RUNNING ou STOP

	protected String nom; // nom de la structure

	private List<Coordonnees> coos;
	public static int tileSize = 16;
	Random random;
	float[] color;
	private Texture texture; 
	
	/**
	 * @param nom
	 * Le constructeur doit être appelé depuis la classe fille avec comme paramétre le nom du la structure
	 * Constructeur sans image: une image par défaut est chargée pour l'affichage
	 */
	public Structure(String nom, List<Coordonnees> coos) {
		this.nom = nom;
		this.etat = Etat.RUNNING;
		this.coos =  coos;
		random = new Random();
		
		//color = new float[]{random.nextFloat(), random.nextFloat(), random.nextFloat(), 1.0f};
		color = new float[]{1,1,1,1};

		texture = TypeStructures.valueOf(nom.toUpperCase()).getTexture();
		
	}


	public Etat getEtat() {
		return etat;
	}


	public void setEtat(Etat etat) {
		this.etat = etat;
	}



	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	@Override
	public void utiliser(Ressources res) {
		System.out.println("Je ne suis pas implémenté ! " + nom);
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


	public List<Coordonnees> getCoos() {
		return coos;
	}


	public void setCoos(List<Coordonnees> coos) {
		this.coos = coos;
	}


	

}
