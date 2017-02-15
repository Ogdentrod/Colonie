package fr.benoitsepe.colonie.main;

import fr.benoitsepe.colonie.ressources.Ressources;
import fr.benoitsepe.colonie.structures.Structure;
import fr.benoitsepe.colonie.zone.Zone;

/**
 * @author Benoît
 * Class temporaire pour l'affichage des données. On pourra la remplacer par la GUI
 */
public class Affichage { 
	
	/**
	 * @param struct
	 * Affichage des infos de la structure
	 */
	public static void afficherStruct(Zone struct) {
		System.out.println();
		System.out.println(struct.getNom());
		System.out.println(" Etat = " + struct.getEtat());

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


	public static void afficherStruct(Structure elem) {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println(elem.getNom());
		System.out.println(" Maintenance = " + elem.getMaintenance() + "%");
	}
}
