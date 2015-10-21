package fr.benoitsepe.colonie.main;

import fr.benoitsepe.colonie.structures.exterieur.Eolienne;

/**
 * @author Beno�t
 * @description Classe permettant de construire la structure en consommant les ressources
 */
public class Gestion {
	
	Ressources res;
	
	

	public Gestion() {
		this.res = new Ressources();
	}
	
	public void creerStruct(TypeStructures struct) {
		Ressources.utiliserRessources(res, struct.getRes());
		
		if(struct == TypeStructures.EOLIENNE) {
			// Stocker dans une list ou map
			Eolienne eolienne = new Eolienne();
			Affichage.afficherStruct(eolienne);
		}

	}
	
	
}
