package fr.benoitsepe.colonie.main;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		// Je t'ai mis un exemple de création d'une éolienne, lance le programme pour voir
		
		Gestion moteur = new Gestion(); // Init moteur
		
		
		moteur.creerStruct(TypeStructures.EOLIENNE); // Tu peux en créer plusieurs si tu veux, j'ai pas encore implémenté les autres structures
	}

}
