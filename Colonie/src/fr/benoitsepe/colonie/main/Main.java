package fr.benoitsepe.colonie.main;

import fr.benoitsepe.colonie.structures.interieur.Oxygene;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Ressources res = new Ressources(); // Initialisation des ressources
		Oxygene s1 = new Oxygene(); // Création d'une structure
		AfficherEtat.afficherEtat(s1);
	}

}
