package fr.benoitsepe.colonie.main;

/**
 * @author Beno�t
 * Class temporaire pour l'affichage des donn�es. On pourra la remplacer par la GUI
 */
public class Affichage { 
	
	/**
	 * @param struct
	 * Affichage des infos de la structure
	 */
	public static void afficherStruct(Structure struct) {
		System.out.println();
		System.out.println(struct.getNom());
		System.out.println(" Etat = " + struct.getEtat());
		System.out.println(" Maintenance = " + struct.getMaintenance() + "%");
	}
}
