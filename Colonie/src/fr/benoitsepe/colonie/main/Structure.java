package fr.benoitsepe.colonie.main;

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
	
	
	/**
	 * @param nom
	 * Le constructeur doit �tre appel� depuis la classe fille avec comme param�tre le nom du la structure
	 */
	public Structure(String nom) {
		this.nom = nom;
		etat = Etat.RUNNING;
		maintenance = 100;
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
		// TODO Auto-generated method stub
		System.out.println("Je ne suis pas impl�ment� ! " + nom);
	}
	
	

}
