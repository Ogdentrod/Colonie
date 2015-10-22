package fr.benoitsepe.colonie.main;

/**
 * @author Benoît
 * Class temporaire pour l'affichage des données. On pourra la remplacer par la GUI
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
	
	
	/**
	 * @param res
	 * Affichage des ressources
	 */
	public static void afficherRessource(Ressources res) {
		System.out.println();
		System.out.println("Vous disposez de :");
		System.out.println(" " + res.getWater() + " eau(x)");
		System.out.println(" " + res.getOxygen() + " O2");
		System.out.println(" " + res.getIron() + " fer(s)");
		System.out.println(" " + res.getElec() + " coulombs");
	}
}
