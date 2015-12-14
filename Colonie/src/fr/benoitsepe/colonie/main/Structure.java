package fr.benoitsepe.colonie.main;

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
	protected int maintenance; // 0 = cassé, 100=neuf
	protected String nom; // nom de la structure
	protected int sizeX; //Taille horizontale de la structure
	protected int sizeY; //Taille verticale de la structure
	
	/**
	 * @param nom
	 * Le constructeur doit être appelé depuis la classe fille avec comme paramétre le nom du la structure
	 * Constructeur sans image: une image par défaut est chargée pour l'affichage
	 */
	public Structure(String nom) {
		this.nom = nom;
		etat = Etat.RUNNING;
		maintenance = 100;
		sizeX=1;	
		sizeY=1;
	}
	
	public int getSizeX() {
		return sizeX;
	}


	public int getSizeY() {
		return sizeY;
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
		System.out.println("Je ne suis pas implémenté ! " + nom);
	}
	
	public void connect(Structure struct){
		
	}

}
