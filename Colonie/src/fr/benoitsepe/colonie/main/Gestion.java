package fr.benoitsepe.colonie.main;

import fr.benoitsepe.colonie.structures.exterieur.Eolienne;
import fr.benoitsepe.colonie.structures.exterieur.PanneauSolaire;
import fr.benoitsepe.colonie.structures.interieur.Refectoire;
import fr.benoitsepe.colonie.structures.interieur.Sas;
import fr.benoitsepe.colonie.structures.interieur.UsineOxygene;

/**
 * @author Benoît
 * @description Classe permettant de construire la structure en consommant les ressources
 */
public class Gestion {
	
	public static final int ARRAY_LONGUEUR = 50;
	public static final int ARRAY_HAUTEUR = 50;
	
	Ressources res;
	
	Structure[][] structures = new Structure[ARRAY_HAUTEUR][ARRAY_LONGUEUR];

	public Gestion() {
		this.res = new Ressources();
	}
	
	public void creerStruct(TypeStructures struct, int posX, int posY) {
		
		Ressources.utiliserRessources(res, struct.getRes()); // On dépense les ressources (faire les vérif)
		Structure structCree;
		
		switch(struct) {
			case EOLIENNE:
				structCree = new Eolienne();
				break;
			case PANNEAU_SOLAIRE:
				structCree = new PanneauSolaire();
				break;
			case REFECTOIRE:
				structCree = new Refectoire();
				break;
			case SAS:
				structCree = new Sas();
				break;
			case USINE_OXYGENE:
				structCree = new UsineOxygene();
				break;
			default:
				System.out.println("ERREUR : Structure non implémentée !");
				structCree = null;
				break;
			
		}
		
		structures[posY][posX] = structCree;
		
		Affichage.afficherStruct(structCree); // instruction temporaire pour affichage
		Affichage.afficherRessource(res);

		

	}
	
	
}
